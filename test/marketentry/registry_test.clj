(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 860000.0}]
    (is (== 860000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 999000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "SSD" 0)
        s (registry/register-submit "eng-1" "SSD" 0)]
    (is (= "SSD-DFT-000000" (get d "draft_number")))
    (is (= "SSD-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "SSD" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

(deftest external-beneficiary-registration-compliant-requires-all-three
  (testing "ALL three registration facts must hold -- AND, not OR"
    (is (true? (registry/external-beneficiary-registration-compliant?
                {:contractor-registered-in-south-sudan? true
                 :insurer-registered-in-south-sudan? true
                 :carriage-registered-in-south-sudan? true})))
    (is (false? (registry/external-beneficiary-registration-compliant?
                 {:contractor-registered-in-south-sudan? true
                  :insurer-registered-in-south-sudan? true
                  :carriage-registered-in-south-sudan? false})))
    (is (false? (registry/external-beneficiary-registration-compliant?
                 {:contractor-registered-in-south-sudan? true})))
    (is (false? (registry/external-beneficiary-registration-compliant? {})))))

(deftest external-beneficiary-registration-noncompliant-claim-is-entity-scope-gated
  (testing "an engagement NOT declared :external-beneficiary-procurement? is never flagged, even if it would fail compliance"
    (is (false? (registry/external-beneficiary-registration-noncompliant-claim?
                 {:external-beneficiary-procurement? false}))))
  (testing "an external-beneficiary-procurement engagement that fails any one registration branch -> noncompliant claim"
    (is (true? (registry/external-beneficiary-registration-noncompliant-claim?
                {:external-beneficiary-procurement? true
                 :contractor-registered-in-south-sudan? true
                 :insurer-registered-in-south-sudan? false
                 :carriage-registered-in-south-sudan? false}))))
  (testing "an external-beneficiary-procurement engagement that DOES satisfy all three -> not flagged"
    (is (false? (registry/external-beneficiary-registration-noncompliant-claim?
                 {:external-beneficiary-procurement? true
                  :contractor-registered-in-south-sudan? true
                  :insurer-registered-in-south-sudan? true
                  :carriage-registered-in-south-sudan? true})))))
