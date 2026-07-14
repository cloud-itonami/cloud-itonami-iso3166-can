(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest can-has-spec-basis
  (let [sb (facts/spec-basis "CAN")]
    (is (= 3 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://laws-lois.justice.gc.ca/") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["CAN" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["can.labour-code"]
         (mapv :statute/id (facts/by-topic "CAN" :labor))))
  (is (empty? (facts/by-topic "CAN" :environment)))
  (is (empty? (facts/by-topic "ATL" :labor))))
