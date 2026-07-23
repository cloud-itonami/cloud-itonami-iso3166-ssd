(ns marketentry.governor
  "Market-Entry Compliance Governor -- the independent compliance layer
  that earns the MarketEntry-LLM the right to commit. The LLM has no
  notion of South Sudan procurement law, whether a claimed engagement
  fee actually equals base + months x rate, whether an engagement
  declared as externally-beneficiary-favouring treaty-funded
  procurement actually satisfies the Public Procurement and Disposal of
  Assets Act, 2018's own Section 4(7)-(8) local-registration
  requirements, whether a Ministry of Finance Tax Identification Number
  (TIN) record has been verified for a filing that requires it, or when
  a draft stops being a draft and becomes a real-world
  eservices.gov.ss/investment submission, so this MUST be a separate
  system able to *reject* a proposal and fall back to HOLD.

  `:itonami.blueprint/governor` is `:market-entry-compliance-governor`
  (shared family keyword on blueprints).

  This blueprint's own text (docs/business-model.md Trust Controls:
  'any actual portal registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off'; 'a false or fabricated regulatory-requirement claim
  is a HARD hold') names exactly the checks below.

  Six checks, in priority order, ALL HARD violations: a human
  approver CANNOT override them. The confidence/actuation gate is
  SOFT: it asks a human to look (low confidence / actuation), and the
  human may approve -- but see `marketentry.phase`: for `:stake
  :actuation/draft-filing`/`:actuation/submit-filing` NO phase ever
  allows auto-commit either. Two independent layers agree that
  actuation is always a human call.

    1. Spec-basis                  -- did the jurisdiction proposal cite
                                       an OFFICIAL source
                                       (`marketentry.facts`), or invent
                                       one?
    2. Evidence incomplete         -- for `:filing/draft`/
                                       `:filing/submit`, has the
                                       jurisdiction actually been
                                       assessed with a full evidence
                                       checklist on file?
    3. External-beneficiary
       registration noncompliant   -- for `:filing/submit`, when the
                                       engagement declares
                                       `:external-beneficiary-
                                       procurement? true` (i.e. it is
                                       procurement funded under a
                                       treaty/agreement favouring an
                                       external beneficiary),
                                       INDEPENDENTLY recompute whether
                                       ALL THREE of the engagement's own
                                       declared registration facts
                                       (contractor / insurer / carriage
                                       all registered in South Sudan)
                                       satisfy the Public Procurement
                                       and Disposal of Assets Act,
                                       2018's own Section 4(7)-(8), and
                                       HARD-hold if not ALL THREE hold.
                                       FLAGSHIP genuinely new check --
                                       an AND-of-three-independent-
                                       boolean-registration-facts
                                       COMPLIANCE test, a check SHAPE
                                       different from the CAF sibling
                                       this repo's scaffold was modeled
                                       on (CAF's Marché réservé check is
                                       an OR-of-thresholds ELIGIBILITY
                                       test) -- see
                                       `marketentry.registry`'s
                                       docstring for the full research
                                       trail and the honestly-disclosed
                                       limit on how far this was grepped
                                       across the wider fleet.
    4. Engagement fee mismatch     -- for `:filing/submit`,
                                       INDEPENDENTLY recompute whether
                                       the engagement's own `:claimed-
                                       fee` equals `base-fee +
                                       monthly-rate x monitoring-
                                       months` -- honest reapplication
                                       of the ground-truth-recompute
                                       discipline sibling actors use.
    5. TIN record unverified        -- for `:filing/submit`, when the
                                       engagement declares
                                       `:requires-tin-record? true`,
                                       INDEPENDENTLY check
                                       `:tin-record-verified?`.
                                       CONDITIONAL on the engagement's
                                       own ground truth. Grounded in the
                                       Ministry of Finance's Tax
                                       Identification Number procedure
                                       (see `marketentry.facts`, sourced
                                       from the World Bank Doing
                                       Business 2020 South Sudan
                                       profile's own procedure table).
    6. Confidence floor / actuation
       gate                          -- LLM confidence below threshold,
                                       OR the op is `:filing/draft`/
                                       `:filing/submit` (REAL acts)
                                       -> escalate.

  Two more guards, double-draft/double-submit prevention, are enforced
  off dedicated `:drafted?`/`:submitted?` facts (never a `:status`
  value)."
  (:require [marketentry.facts :as facts]
            [marketentry.registry :as registry]
            [marketentry.store :as store]))

(def confidence-floor 0.6)

(def high-stakes
  "Stakes grave enough to always require a human, even when clean.
  Drafting a real portal package and submitting a real portal
  registration are the two real-world actuation events this actor
  performs."
  #{:actuation/draft-filing :actuation/submit-filing})

;; ----------------------------- checks -----------------------------

(defn- spec-basis-violations
  "A `:jurisdiction/assess` (or `:filing/draft`/`:filing/submit`)
  proposal with no spec-basis citation is a HARD violation -- never
  invent a jurisdiction's market-entry requirements."
  [{:keys [op]} proposal]
  (when (contains? #{:jurisdiction/assess :filing/draft :filing/submit} op)
    (let [value (:value proposal)]
      (when (or (empty? (:cites proposal))
                (and (contains? value :spec-basis) (nil? (:spec-basis value))))
        [{:rule :no-spec-basis
          :detail "公式spec-basisの引用が無い提案は法域要件として扱えない"}]))))

(defn- evidence-incomplete-violations
  "For `:filing/draft`/`:filing/submit`, the jurisdiction's required
  registration evidence must actually be satisfied."
  [{:keys [op subject]} st]
  (when (contains? #{:filing/draft :filing/submit} op)
    (let [e (store/engagement st subject)
          assessment (store/assessment-of st subject)]
      (when-not (and assessment
                     (facts/required-evidence-satisfied?
                      (:jurisdiction e) (:checklist assessment)))
        [{:rule :evidence-incomplete
          :detail "法域の必要書類(Business Registry登記/Ministry of Finance TIN/Chamber of Commerce登録/対外受益者調達地元登録確認/代理人確認等)が充足していない状態での提案"}]))))

(defn- external-beneficiary-registration-noncompliant-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own declared registration facts satisfy the Public
  Procurement and Disposal of Assets Act, 2018's own Section 4(7)-(8) --
  the flagship check this vertical adds. HARD-hold when the engagement
  declares `:external-beneficiary-procurement? true` but is not
  independently compliant (i.e. NOT all three of contractor/insurer/
  carriage registered in South Sudan)."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (registry/external-beneficiary-registration-noncompliant-claim? e)
        [{:rule :external-beneficiary-registration-noncompliant
          :detail (str subject " は対外受益者向け条約資金調達(external-beneficiary procurement)を宣言しているが、"
                      "独立再計算(請負業者・保険会社・輸送手段の全てが南スーダン登録であること)"
                      "による適合性を満たさない(Public Procurement and Disposal of Assets Act, 2018 Section 4(7)-(8))")}]))))

(defn- engagement-fee-mismatch-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own claimed fee equals base + months x rate."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when-not (registry/engagement-fee-matches-claim? e)
        [{:rule :engagement-fee-mismatch
          :detail (str subject " の申告手数料(" (:claimed-fee e)
                      ")が独立再計算値(" (registry/compute-engagement-fee e) ")と一致しない")}]))))

(defn- tin-record-unverified-violations
  "For `:filing/submit`, when the engagement declares
  `:requires-tin-record? true`, INDEPENDENTLY check
  `:tin-record-verified?` -- CONDITIONAL on the engagement's own
  ground truth. Grounded in the Ministry of Finance's Tax
  Identification Number procedure."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when (and (true? (:requires-tin-record? e))
                 (not (true? (:tin-record-verified? e))))
        [{:rule :tin-record-unverified
          :detail (str subject " はMinistry of Finance TIN(Tax Identification Number)記録の確認を要するが未確認 -- 提出提案は進められない")}]))))

(defn- already-drafted-violations
  "For `:filing/draft`, refuses to draft the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/draft)
    (when (store/engagement-already-drafted? st subject)
      [{:rule :already-drafted
        :detail (str subject " は既にドラフト済み")}])))

(defn- already-submitted-violations
  "For `:filing/submit`, refuses to submit the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (when (store/engagement-already-submitted? st subject)
      [{:rule :already-submitted
        :detail (str subject " は既に提出済み")}])))

(defn check
  "Censors a MarketEntry-LLM proposal against the governor rules.
  Returns {:ok? bool :violations [..] :confidence c :escalate? bool
  :high-stakes? bool :hard? bool}."
  [request _context proposal st]
  (let [hard (into []
                   (concat (spec-basis-violations request proposal)
                           (evidence-incomplete-violations request st)
                           (external-beneficiary-registration-noncompliant-violations request st)
                           (engagement-fee-mismatch-violations request st)
                           (tin-record-unverified-violations request st)
                           (already-drafted-violations request st)
                           (already-submitted-violations request st)))
        conf (:confidence proposal 0.0)
        low? (< conf confidence-floor)
        stakes? (boolean (high-stakes (:stake proposal)))
        hard? (boolean (seq hard))]
    {:ok?          (and (not hard?) (not low?) (not stakes?))
     :violations   hard
     :confidence   conf
     :hard?        hard?
     :escalate?    (and (not hard?) (or low? stakes?))
     :high-stakes? stakes?}))

(defn hold-fact
  "The audit fact written when a proposal is rejected (HOLD)."
  [request context verdict]
  {:t          :governor-hold
   :op         (:op request)
   :actor      (:actor-id context)
   :subject    (:subject request)
   :disposition :hold
   :basis      (mapv :rule (:violations verdict))
   :violations (:violations verdict)
   :confidence (:confidence verdict)})
