(ns aoc-clj.utils
  (:require [clojure.java.io :as io]))


(defn- read-file [input]
  (slurp (io/resource input)))


(defn read-input [day]
  (read-file (format "day%02d.txt" day)))



(defn read-test [day]
  (read-file (format "day%02d_test.txt" day)))



(defn to-int [char]
  (Integer/parseInt char))                      