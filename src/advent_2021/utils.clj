(ns advent-2021.utils
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn input-lines
  [file-name]
  (string/split-lines (slurp (io/resource file-name))))
