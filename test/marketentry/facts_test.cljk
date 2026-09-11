(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest ssd-has-spec-basis
  (let [sb (facts/spec-basis "SSD")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/corporate-number-spec-basis "SSD")))
    (is (some? (facts/external-beneficiary-registration-spec-basis "SSD")))))

(deftest ssd-rep-spec-basis-is-honestly-absent
  (testing "Section 50 (Exclusion of Providers and Contractors) is a plausible location for such a provision, but this iteration only read its title off the table of contents, not its text -- deliberately not claimed"
    (is (nil? (facts/rep-spec-basis "SSD")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "SSD")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "SSD" all)))
    (is (not (facts/required-evidence-satisfied? "SSD" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["SSD" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 2 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))

(deftest external-beneficiary-registration-spec-basis-criteria
  (let [rm (facts/external-beneficiary-registration-spec-basis "SSD")]
    (is (contains? (:external-beneficiary-registration-criteria rm) :contractor-registered-in-south-sudan?))
    (is (contains? (:external-beneficiary-registration-criteria rm) :insurer-registered-in-south-sudan?))
    (is (contains? (:external-beneficiary-registration-criteria rm) :carriage-registered-in-south-sudan?))
    (is (= 3 (count (:external-beneficiary-registration-criteria rm))))))
