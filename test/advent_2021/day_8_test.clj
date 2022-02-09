(ns advent-2021.day-8-test
  (:require [clojure.test :refer :all]
            [advent-2021.day-8 :as day-8]
            [advent-2021.utils :as utils]
            [clojure.string :as string]
            [clojure.java.io :as io]))

(defn parse-data
  [line]
  (let [[input output] (->> (string/split line
                                          #"\|")
                            (map #(format "[%s]" %))
                            (map read-string))]
    {:input (map str input)
     :output (map str output)}))

(deftest day-8-test
  (testing "day 8 part 1"
        (let [data "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"
          lines (string/split data
                              #"\n")]
          (day-8/output-matching-digit-count (map parse-data
                                                  lines)
                                             #{1 4 7 8}))
        (let [lines (utils/input-lines "day_8.txt")]
          (day-8/output-matching-digit-count (map parse-data
                                                  lines)
                                             #{1 4 7 8})))
  (testing "day 8 part 2"
        (let [data "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"
          lines (string/split data
                              #"\n")]
          (map day-8/output-value
               (map (juxt :input :output)
                    (map parse-data
                         lines))))
        (let [lines (utils/input-lines "day_8.txt")]
          ;;(day-8/output-value
          (clojure.pprint/pprint (map (juxt :input :output)
                                      (map parse-data
                                           lines)))))
        ;;)
  )


(defn parse [f]
  (->> f
       io/resource
       slurp
       string/trim
       string/split-lines
       (map #(string/split % #"\|"))
       (map (fn [[i o]] [(string/split (string/trim i) #"\s")
                         (string/split (string/trim o) #"\s")]))
       (into [])))

(deftest test-output-value
  (is (= (reduce + (map #(day-8/output-value %)
                        (parse "day_8.txt")))
         61229)))
