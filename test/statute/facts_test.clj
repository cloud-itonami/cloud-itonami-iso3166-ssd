(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest ssd-has-spec-basis
  (let [sb (facts/spec-basis "SSD")]
    (is (= 3 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["SSD" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["ssd.labour-act-2017"]
         (mapv :statute/id (facts/by-topic "SSD" :labor))))
  (is (= ["ssd.investment-promotion-act-2009"]
         (mapv :statute/id (facts/by-topic "SSD" :investment))))
  (is (empty? (facts/by-topic "SSD" :data-protection))
      "no data-protection/tax statute could be independently verified this iteration -- honestly absent, see namespace docstring")
  (is (empty? (facts/by-topic "ATL" :labor))))
