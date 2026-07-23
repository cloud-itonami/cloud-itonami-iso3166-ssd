(ns marketentry.facts
  "Per-jurisdiction public-procurement market-entry regulatory catalog
  -- the G2-style spec-basis table the Market-Entry Compliance Governor
  checks every `:jurisdiction/assess` proposal against ('did the advisor
  cite an OFFICIAL public source for this jurisdiction's requirements,
  or did it invent one?').

  South Sudan's real market-entry surface (WebFetch/curl-verified
  2026-07-23; South Sudan is a genuinely thin-documentation jurisdiction
  -- independence 2011, most ministry websites this iteration tried were
  either unreachable (DNS failure), returned bare/near-empty SPA shells,
  or 403. Where a source could not be reached, or a ministry site turned
  out to be a placeholder, that is stated explicitly rather than
  silently omitted. This session's WebSearch tool was unavailable
  (fleet-wide budget exhausted by concurrent sibling work) -- research
  here relies on direct WebFetch/curl of primary and near-primary
  sources, plus the Wayback Machine (`web.archive.org`, fetched via
  plain `curl`, NOT WebFetch -- this session's WebFetch tool hard-blocks
  the `web.archive.org` host entirely, unrelated to bot-detection):

  - **Public procurement**: this iteration found and directly read the
    actual government-printed primary text of the 'Public Procurement
    and Disposal of Assets Act, 2018' (`LAWS OF SOUTH SUDAN`, 226
    Articles / 7 Chapters), obtained from FAO's FAOLEX legal database
    (`faolex.fao.org/docs/pdf/ssd220644E.pdf`) and cross-confirmed via
    `ecolex.org` (the joint FAO/IUCN/UNEP legal database;
    `ecolex.org/details/legislation/public-procurement-and-disposal-of-
    assets-act-2018-lex-faoc220644/` -- same FAOLEX id, independently
    discovered via a Wayback CDX listing of `faolex.fao.org/docs/pdf/
    ssd*` files BEFORE the ecolex search corroborated it). Own preamble
    (read directly): 'In accordance with the provisions of Articles
    55(3)(b), 110(n), and 85(1) of the Transitional Constitution of the
    Republic of South Sudan, 2011 (amended 2018), the Transitional
    National Legislative Assembly, with the assent of the President of
    the Republic of South Sudan, hereby enacts the following.' Own
    Section 1: 'This Act shall be cited as the \"Public Procurement and
    Disposal of Assets Act, 2018\" and shall come into force on the date
    of its signature by the President.' Own Section 5 (Interpretations,
    read directly): '\"Authority\" means the South Sudan Public
    Procurement and Disposal of Assets Authority established under this
    Act.' -- this iteration deliberately does NOT abbreviate this to
    'PPDA' anywhere in this catalog: the Act's own text never uses that
    abbreviation for itself (Uganda's OWN procurement authority is
    separately and coincidentally also abbreviated 'PPDA' --
    Public Procurement and Disposal of Public Assets Authority -- and
    this repo's pre-existing `organization.edn` carried an unverified
    'PPDA' head-role claim that this iteration believes was cross-
    contaminated from the Uganda sibling scaffold and has NOT propagated
    into this catalog). ecolex's own secondary abstract states entry
    into force as '26 April 2019 (date of Presidential signature)' --
    this iteration did NOT independently read the Act's own final Assent
    page to re-confirm that exact date (MODERATE confidence on the exact
    date; HIGH confidence on every other fact quoted above, all read
    directly off the Act's own Chapter I text).
  - **This iteration specifically investigated whether South Sudan has a
    dedicated e-procurement SELF-SERVICE portal and found no evidence of
    one.** No portal domain was discoverable via direct fetch or Wayback
    CDX. The Ministry of Finance and Planning's own domain (`mof.gov.ss`)
    failed outright (DNS resolution failure, both live and via Wayback
    CDX). This iteration reports this as an honest gap, not a claim that
    no publication channel exists.
  - **This iteration independently discovered, via Wayback Machine CDX
    enumeration of `*.gov.ss`, that South Sudan runs a real unified
    e-Government portal at `eservices.gov.ss`** with live (as of the
    most recent crawl) subdomains for Business Registry Service
    (`brs.eservices.gov.ss`), Revenue Authority (`nra.eservices.gov.ss`),
    Customs (`customs.eservices.gov.ss`), Trade
    (`trade.eservices.gov.ss`), and Investment
    (`investment.eservices.gov.ss`) -- all confirmed to exist (HTTP 200
    snapshots), though most render only as SPA login/app shells with no
    extractable legal-citation text. The `investment.eservices.gov.ss`
    homepage (Wayback snapshot 2024-07-10, read directly, footer dated
    '© 2020') is the one exception with real prose, confirming in the
    government's own words: 'Welcome to the Ministry of Investment / The
    Ministry of Investment is accountable for formulating strategies,
    policies, goals and programs aimed at developing local and foreign
    investments in South Sudan' and 'Application for the certificate of
    investment / Individuals and organizations can apply for this
    certificate.' This independently corroborates the 'Investment
    Certificate' mechanism this iteration also found directly in the
    Investment Promotion Act, 2009's own Chapter VI (see below).
  - **Business/company registration**: the World Bank's own 'Doing
    Business 2020' South Sudan Economy Profile (data collection
    completed May 2019; NOTE the Doing Business project itself was
    discontinued by the World Bank in 2021 following a data-integrity
    review -- this iteration cites it as a dated but real, directly-read
    primary-source-adjacent government-procedure survey, not as a
    currently-maintained index) was fetched directly
    (`archive.doingbusiness.org/content/dam/doingBusiness/country/s/
    south-sudan/SSD.pdf`) and read in full for the 'Starting a Business'
    section. Its own procedure table (read directly, quoted verbatim)
    names the 'Business Registry' as the agency issuing the Certificate
    of Incorporation ('Pay the fees and obtain the Certificate of
    Incorporation from the Business Registry ... After approval by the
    legal counselors, the certificate of registration is printed and
    signed by the Registrar') and separately states 'The Company Act of
    2012 requires companies to obtain a company seal.' This iteration
    ALSO independently found, via `ecolex.org`, a distinct catalog entry
    titled 'Companies Act 2012' (FAOLEX id LEX-FAOC221075,
    `faolex.fao.org/docs/pdf/ssd221075E.pdf`) -- an independent
    corroboration of the Act's existence and year from a second catalog
    -- but this iteration's own attempt to read that FAOLEX PDF's
    primary text directly rendered as blank scanned pages (a scan/OCR
    artifact of that specific file, not a fabrication), so the exact
    section numbering of the company-seal requirement is NOT
    independently confirmed by this iteration beyond the World Bank's
    own quote. This iteration ALSO independently confirmed, directly
    from the Investment Promotion Act, 2009's own Section 5 definitions
    (read directly, see `statute.facts`), that a 'Chief Registrar of
    Companies, Businesses, Non-Governmental Organizations, Societies and
    Association' is a real, named statutory office -- consistent with,
    or the predecessor of, the 'Business Registry' the Doing Business
    report names, though this iteration did NOT find a document that
    states these are the identical office under identical names, so the
    two are cited separately rather than conflated.
  - **Tax registration**: this iteration confirmed the 'South Sudan
    Revenue Authority' is a real, live institution by fetching
    `nra.gov.ss` directly (self-titled 'South Sudan Revenue Authority'
    in its own page title; the page otherwise rendered near-empty, an
    honest limitation of what this iteration could extract). The World
    Bank Doing Business report (read directly) states companies must
    'Register with the Ministry of Finance and request a Tax
    Identification Number (TIN)' -- own quote: 'Companies need to
    request a TIN number from the Ministry of Finance ... Upon obtaining
    a TIN, all companies are required to pay an amount equal to 10% of
    their monthly rent as a tax.' This iteration searched specifically
    (via `ecolex.org`) for a South Sudan 'Taxation Act' and found
    NOTHING -- an honest, explicitly-reported GAP: this iteration could
    NOT independently verify the name, number, or year of any South
    Sudan tax-code statute this session. `corporate-number-legal-basis`
    below therefore cites only the Doing Business procedure quote and
    the live Revenue Authority website, WITHOUT asserting a specific
    Taxation Act citation this iteration could not confirm.
  - This iteration also looked for a South Sudan-specific representative
    /director exclusion-extension provision (the shape Bulgaria's ЗОП
    Art. 54(2)-(3) / Benin's Art. 61/62 / CAR's now-superseded 2008 Code
    Arts. 17-19 document for their own laws). The Public Procurement and
    Disposal of Assets Act, 2018's own table of contents lists a
    'Section 50: Exclusion of Providers and Contractors from the
    Procurement Process' -- a plausible location for such a provision --
    but this iteration did NOT read that specific section's text this
    session (only its title, off the table of contents). Rather than
    guess its content, `rep-spec-basis` below is left honestly nil for
    SSD, the same discipline CAR's and Bhutan's own catalogs use when a
    provision may exist but this iteration cannot confirm its current,
    citable shape.
  - `external-beneficiary-registration-spec-basis` grounds this
    vertical's FLAGSHIP check (see `marketentry.governor` /
    `marketentry.registry`) -- a genuinely South-Sudan-specific
    mechanism this iteration found directly in the Act's own Section 4,
    subsections (7)-(8) (read directly, quoted in full in the catalog
    entry below): when procurement funded through a treaty or agreement
    to which South Sudan is a party 'favours an external beneficiary',
    the Act requires ALL of: the procurement is undertaken in South
    Sudan through contractors registered in South Sudan, relevant
    insurances are placed with companies registered in South Sudan, AND
    goods are transported in carriages registered in South Sudan. This
    is an AND-of-three-independent-registration-requirements test --
    this iteration did not exhaustively grep the full ~150-repo
    iso3166 fleet to confirm this exact check SHAPE is globally unique
    (that would require cloning many more sibling repos than this
    iteration's time budget allowed), but it is at minimum genuinely
    different in shape from the sibling this iteration DID clone and
    read in full (CAR's Marché réservé check is an OR-of-workforce-
    percentage-thresholds/legal-form-membership ELIGIBILITY test; this
    South Sudan check is instead an AND-of-three-independent-boolean-
    registration-facts COMPLIANCE test, gated on a different declared
    entity-fact -- externally-favouring treaty-funded procurement,
    rather than reserved-contract bidding).

  Coverage is reported HONESTLY (see `coverage`): a jurisdiction not in
  this table has NO spec-basis, full stop -- the advisor must not
  fabricate one, and the governor holds if it tries.")

(def catalog
  "iso3 -> requirement map. `:required-evidence` mirrors the generic
  intake/portal-registration/filing evidence set; `:legal-basis` /
  `:owner-authority` / `:provenance` are the G2 citation the governor
  requires before any `:jurisdiction/assess` proposal can commit. SSD
  deliberately carries NO `:rep-owner-authority` -- see the namespace
  docstring's honest-scope-narrowing note (a plausible provision
  location exists in the Act's own table of contents, Section 50, but
  this iteration did not read that section's text this session).
  `:external-beneficiary-registration-owner-authority` /
  `:external-beneficiary-registration-legal-basis` /
  `:external-beneficiary-registration-criteria` /
  `:external-beneficiary-registration-provenance` ground this
  vertical's flagship governor check
  (`external-beneficiary-registration-compliant?`/
  `external-beneficiary-registration-noncompliant-claim?` in
  `marketentry.registry`)."
  {"SSD" {:name "South Sudan"
          :owner-authority "South Sudan Public Procurement and Disposal of Assets Authority -- the national procurement regulator established under the Public Procurement and Disposal of Assets Act, 2018 (this Act's own text never abbreviates this to an acronym; this catalog deliberately does not use 'PPDA' for South Sudan -- see namespace docstring)"
          :legal-basis "Public Procurement and Disposal of Assets Act, 2018 (Transitional National Legislative Assembly, with the assent of the President of the Republic of South Sudan, per Articles 55(3)(b)/110(n)/85(1) of the Transitional Constitution of the Republic of South Sudan, 2011 (amended 2018)) -- own Section 1: comes into force on the date of its signature by the President (ecolex.org's secondary abstract states 26 April 2019; this iteration did not independently re-confirm that exact date against the Act's own Assent page, MODERATE confidence on the date only); own Section 5 establishes and names the South Sudan Public Procurement and Disposal of Assets Authority; own Section 4(7)-(8) is this catalog's flagship-check basis (see below)"
          :national-spec "Procuring Entities, Procurement Committees, and Public Procurement and Disposal of Assets Units within each contracting government institution (Chapter III of the Act). No dedicated self-service e-procurement portal domain was independently found by this iteration (an honest gap, unlike e.g. Benin's marches-publics.bj/SIGMAP or Bhutan's egp.gov.bt); the Ministry of Finance and Planning's own domain (mof.gov.ss) failed to resolve, both live and in the Wayback Machine, this session"
          :provenance "https://faolex.fao.org/docs/pdf/ssd220644E.pdf ; https://www.ecolex.org/details/legislation/public-procurement-and-disposal-of-assets-act-2018-lex-faoc220644/"
          :required-evidence ["Business Registry Certificate of Incorporation record (per the World Bank Doing Business 2020 South Sudan profile's own procedure table, read directly; the live registration portal this iteration found is brs.eservices.gov.ss, part of the Republic of South Sudan's eservices.gov.ss e-Government portal)"
                              "Ministry of Finance Tax Identification Number (TIN) record (per the same Doing Business procedure table; this iteration could NOT independently verify a specific Taxation Act citation -- honest gap, see namespace docstring)"
                              "Chamber of Commerce registration record (required, per Doing Business, for tax clearance and certain commercial operations)"
                              "Investment Certificate record, when the engagement is registered with the South Sudan Investment Authority / Ministry of Investment (per the Investment Promotion Act, 2009's own Chapter VI; see statute.facts)"
                              "External-beneficiary procurement local-registration compliance record, when the engagement declares :external-beneficiary-procurement? true (per Public Procurement and Disposal of Assets Act, 2018 Section 4(7)-(8))"
                              "Authorized-representative confirmation record"]
          :corporate-number-owner-authority "Ministry of Finance (and Planning) -- per the World Bank Doing Business 2020 South Sudan profile's own procedure table (read directly): 'Companies need to request a TIN number from the Ministry of Finance'. This iteration also independently confirmed a live 'South Sudan Revenue Authority' (nra.gov.ss, fetched directly, self-titled in its own page) but could not confirm whether TIN issuance itself is a Ministry of Finance function, a Revenue Authority function, or both -- the two are cited separately below rather than conflated"
          :corporate-number-legal-basis "This iteration could NOT independently verify the name, number, or year of any South Sudan Taxation Act this session (searched via ecolex.org; found nothing) -- an honest, explicitly-reported GAP, not a claim that no such law exists. The only directly-read primary-source-adjacent citation available is the World Bank Doing Business 2020 South Sudan profile's own procedure quote: 'Register with the Ministry of Finance and request a Tax Identification Number (TIN) ... Upon obtaining a TIN, all companies are required to pay an amount equal to 10% of their monthly rent as a tax' (data collection completed May 2019)"
          :corporate-number-provenance "https://archive.doingbusiness.org/content/dam/doingBusiness/country/s/south-sudan/SSD.pdf (World Bank Doing Business 2020, South Sudan Economy Profile) ; https://www.nra.gov.ss/ (South Sudan Revenue Authority, live, self-titled)"
          :external-beneficiary-registration-owner-authority "South Sudan Public Procurement and Disposal of Assets Authority / each Procuring Entity applies the requirement to procurement funded under an applicable treaty or agreement"
          :external-beneficiary-registration-legal-basis "Public Procurement and Disposal of Assets Act, 2018, Section 4, subsections (7)-(8) (read directly, quoted in full): '(7) Where the Republic of South Sudan is required under the terms of any treaty or agreement to which it is a party, to contribute from its resources in any form to any procurement activities within South Sudan, either in part or wholly, jointly or separately, procurement through such contributions shall be: (a) in discrete activities where possible; (b) subject to the applicable provisions of this Act; (c) a treaty or agreement referred to in paragraph (a) which contains provisions favourable to citizens and local contractors, and full advantage shall be taken of these in the interest of promoting domestic capacity development; (8) Where procurement to which paragraph (7)(a) favours an external beneficiary: (a) the procurement through contributions made by South Sudan, shall be undertaken in South Sudan through contractors registered in South Sudan, and (b) all relevant insurances shall be placed with companies registered in South Sudan and goods shall be transported in carriages registered in South Sudan.'"
          :external-beneficiary-registration-criteria #{:contractor-registered-in-south-sudan? :insurer-registered-in-south-sudan? :carriage-registered-in-south-sudan?}
          :external-beneficiary-registration-provenance "https://faolex.fao.org/docs/pdf/ssd220644E.pdf (Section 4(7)-(8), read directly)"}
   "USA" {:name "United States"
          :owner-authority "U.S. General Services Administration (GSA) / SAM.gov"
          :legal-basis "Federal Acquisition Regulation (FAR); System for Award Management"
          :national-spec "SAM.gov entity registration + NAICS self-certification"
          :provenance "https://sam.gov/"
          :required-evidence ["EIN record"
                              "SAM.gov registration record"
                              "State business registration record"
                              "Authorized-representative record"]}
   "DEU" {:name "Germany"
          :owner-authority "Beschaffungsamt des BMI / e-Vergabe platforms"
          :legal-basis "Gesetz gegen Wettbewerbsbeschränkungen (GWB) / VgV"
          :national-spec "e-Vergabe supplier registration under EU procurement directives"
          :provenance "https://www.evergabe-online.de/"
          :required-evidence ["Handelsregister extract"
                              "e-Vergabe registration record"
                              "USt-IdNr record"
                              "Authorized-representative record"]}})

(defn spec-basis
  "The jurisdiction's requirement map, or nil -- nil means NO spec-basis,
  and the governor must hold any proposal that tries to assess or file
  on it."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report: how many of the requested jurisdictions actually
  have a spec-basis entry. Never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-ssd R0: " (count catalog)
                 " jurisdictions seeded with an official spec-basis. "
                 "This is a starting catalog for market-entry navigation, "
                 "not a survey of all ~194 jurisdictions -- extend "
                 "`marketentry.facts/catalog`, never fabricate a "
                 "jurisdiction's requirements.")})))

(defn required-evidence-satisfied?
  "Does `submitted` (a set/coll of evidence keywords or strings) satisfy
  every evidence item listed for `iso3`? Missing spec-basis -> never
  satisfied."
  [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (let [need (count required-evidence)
          have (count (filter (set submitted) required-evidence))]
      (= need have))))

(defn evidence-checklist [iso3]
  (:required-evidence (spec-basis iso3) []))

(defn rep-spec-basis
  "The jurisdiction's representative-related requirement map, or nil when
  this catalog has no such regime. For SSD this is deliberately nil --
  see the `catalog` docstring's honest-scope-narrowing note (Section 50
  of the Act, 'Exclusion of Providers and Contractors from the
  Procurement Process', is a plausible location for such a provision,
  but this iteration only read its title off the table of contents, not
  its text)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))

(defn corporate-number-spec-basis
  "The jurisdiction's corporate-number / tax-id regime, or nil."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority
                       :corporate-number-legal-basis
                       :corporate-number-provenance]))))

(defn external-beneficiary-registration-spec-basis
  "The jurisdiction's external-beneficiary-favouring-procurement local-
  registration regime, or nil. For SSD this is real and current -- the
  flagship check this vertical adds is grounded here (Public Procurement
  and Disposal of Assets Act, 2018, Section 4(7)-(8))."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:external-beneficiary-registration-owner-authority sb)
      (select-keys sb [:external-beneficiary-registration-owner-authority
                       :external-beneficiary-registration-legal-basis
                       :external-beneficiary-registration-criteria
                       :external-beneficiary-registration-provenance]))))
