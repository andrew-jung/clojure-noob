(require '[clojure.string])

; Vector of maps
(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

; Add "right" side of hobbit parts.
(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-hobbit-body-parts)
(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

; `let` binds names to values, "let it be"
(let [x 3]
  x)
; => 3

(def cats-list
  ["Jiro" "Jiji" "Redd" "Bi" "Mango" "Nomi"])
(let [cats (take 3 cats-list)]
  cats)
; => ("Jiro" "Jiji" "Redd")

#"regular-expression"
(re-find #"^left-" "left-eye")

; Reduce
(reduce + [1 2 3 4])
; Apply the given function to the first two elements of a sequence. That’s where (+ 1 2) comes from.
; Apply the given function to the result and the next element of the sequence. In this case, the result of step 1 is 3, and the next element of the sequence is 3 as well. So the final result is (+ 3 3) .
; Repeat step 2 for every remaining element in the sequence.

; Reduce also takes optional initial value:
(reduce + 15 [1 2 3 4])

; Lets get violent
(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

(hit asym-hobbit-body-parts)
(hit asym-hobbit-body-parts)
(hit asym-hobbit-body-parts)
