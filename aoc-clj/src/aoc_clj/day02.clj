(ns aoc-clj.day02
  (:require [clojure.string :as str]
            [aoc-clj.utils :as utils]))


(def input (utils/read-input 2))
(def colors [:green :red :blue])
(def bag-of-cubes
  {:red 12
   :green 13
   :blue 14})


(defn find-color [color s]
  (->>
   (re-seq (re-pattern (str #"\d+\s" color)) s)
   (map #(Integer. (re-find #"\d+" %)))
   (apply +)))

(defn parse-set [s]
  {:blue (find-color "blue" s)
   :red (find-color "red" s)
   :green (find-color "green" s)})

(defn split-sets [game]
  (->>
   (str/split game #";")
   (map parse-set)))

(defn parse-game [s]

  {:id (re-find #"\d+" (re-find #"\w+\s\d+" s))
   :sets (split-sets s)})

(defn is-playable? [set bag]
  (not (some neg?
             (for [color colors]
               (let [set-count (color set)
                     bag-count (color bag)]
                 (- bag-count set-count))))))

(defn play-game [game bag]
  (loop [sets (:sets game)]
    (prn :id game)
    (if (empty? sets)
      (:id game)
      (if (not (is-playable? (first sets) bag))
        0
        (recur (rest sets))))))

(defn part-1 [input]
  (reduce + (for [game (->> input
                            str/split-lines
                            (map parse-game))]
              (Integer. (play-game game bag-of-cubes)))))

(part-1 input)

(defn find-power [game]
  (apply *
         (let [sets (:sets game)]
           [(apply max (map #(get % :blue) sets))
            (apply max (map #(get % :red) sets))
            (apply max (map #(get % :green) sets))])))


(defn part-2 [input]
  (->> input
       str/split-lines
       (map parse-game)
       (map find-power)
       (apply +)))
(part-2 input)




