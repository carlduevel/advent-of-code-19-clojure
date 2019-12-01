(ns advent-of-code-19-clojure.core
  (:gen-class)
  (:require [clojure.java.io :as io]))

(defn parse-int [s]
  (Integer/parseInt s))

(defn module-masses []
  (with-open [rdr (-> "masses.txt"
                      io/resource
                      (clojure.java.io/reader ))]
    (->> rdr
         line-seq
         doall
         (map parse-int))))

(defn fuel-for-launch [mass]
  (-> mass
      (/ 3)
      Math/floor
      (- 2)))

(defn total-fuel-for-launch
  [mass]
  (->> mass
       (iterate fuel-for-launch)
       (take-while pos?)
       rest
       (reduce +)))

(defn -main
  [& _args]
  (->> (module-masses)
       (map total-fuel-for-launch)
       (reduce +)
       println))



