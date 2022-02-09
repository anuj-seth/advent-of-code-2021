(ns advent-2021.day-6-test
  (:require [clojure.test :refer :all]
            [advent-2021.day-6 :as day-6]
            [advent-2021.utils :as utils]))

(deftest day-6
  (testing "day 6 part 1"
    (let [start-state [3 4 3 1 2]] 
      (is (= (frequencies [2 3 2 0 1]) (day-6/lantern-fish 1
                                                           start-state))) 
      (is (= (frequencies [1 2 1 6 0 8]) (day-6/lantern-fish 2
                                                             start-state))) 
      (is (= (frequencies [0 1 0 5 6 7 8]) (day-6/lantern-fish 3
                                                               start-state)))
      (is (= (frequencies [6 0 6 4 5 6 7 8 8]) (day-6/lantern-fish 4
                                                                   start-state)))
      (is (= (frequencies [5 6 5 3 4 5 6 7 7 8]) (day-6/lantern-fish 5
                                                       start-state)))
      (is (= (frequencies [4 5 4 2 3 4 5 6 6 7]) (day-6/lantern-fish 6
                                                       start-state)))
      (is (= (frequencies [3 4 3 1 2 3 4 5 5 6]) (day-6/lantern-fish 7
                                                       start-state))) 
      (is (= (frequencies [2 3 2 0 1 2 3 4 4 5]) (day-6/lantern-fish 8
                                                       start-state))) 
      (is (= (frequencies [1 2 1 6 0 1 2 3 3 4 8]) (day-6/lantern-fish 9
                                                         start-state))) 
      (is (= (frequencies [0 1 0 5 6 0 1 2 2 3 7 8]) (day-6/lantern-fish 10
                                                           start-state))) 
      (is (= (frequencies [6 0 6 4 5 6 0 1 1 2 6 7 8 8 8]) (day-6/lantern-fish 12
                                                                               start-state)))
      (is (= (frequencies [5 6 5 3 4 5 6 0 0 1 5 6 7 7 7 8 8]) (day-6/lantern-fish 12
                                                                     start-state))) 
      (is (= (frequencies [4 5 4 2 3 4 5 6 6 0 4 5 6 6 6 7 7 8 8]) (day-6/lantern-fish 13
                                                                         start-state))) 
      (is (= (frequencies [3 4 3 1 2 3 4 5 5 6 3 4 5 5 5 6 6 7 7 8]) (day-6/lantern-fish 14
                                                                           start-state))) 
      (is (= (frequencies [2 3 2 0 1 2 3 4 4 5 2 3 4 4 4 5 5 6 6 7]) (day-6/lantern-fish 15
                                                                           start-state))) 
      (is (=  (frequencies [1 2 1 6 0 1 2 3 3 4 1 2 3 3 3 4 4 5 5 6 8]) (day-6/lantern-fish 16
                                                                              start-state))) 
      (is (= (frequencies [0 1 0 5 6 0 1 2 2 3 0 1 2 2 2 3 3 4 4 5 7 8]) (day-6/lantern-fish 17
                                                                               start-state))) 
      (is (= (frequencies [6 0 6 4 5 6 0 1 1 2 6 0 1 1 1 2 2 3 3 4 6 7 8 8 8 8]) (day-6/lantern-fish 18
                                                                                       start-state))))
    (let [puzzle-data (->> (utils/input-lines "day_6.txt")
                           first
                           (format "[%s]")
                           read-string)]
      (is (= 376194 (apply +
                           (map val (day-6/lantern-fish 80
                                                        puzzle-data)))))
      (is (= 1693022481538
             (apply +
                    (map val (day-6/lantern-fish 256
                                                 puzzle-data))))))))
