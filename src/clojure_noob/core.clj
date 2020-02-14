(ns clojure-noob.core
  (:gen-class))

; Main function, we can run this program with `lein run`
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot"))


; (sequential? [1 2 3]) = True
(defn sum
  "Sums a vector of numbers"
  [a-seq]
  (apply + a-seq)`

; Apply is built in Clojure:
; Applies fn f to the argument list formed by prepending intervening arguments to args.

; We use `do` because in an if/else block Clojure will only evaluate 1 form.
; `when` combination of if/do without an else

(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))

(when true
  (println "Success!")
)

; Clojure has true, false, nil values, can use ? to indicate if nil? true? false?
; Everything but false and nil are truthy.

; Equality operator is `=` vs `==` or `===`

; Use defn to bind name to values
(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])

; Ruby-like way of making error message
(def severity :mild)
(def error-message "OH GOD! IT'S A DISASTER! WE'RE ")
(if (= severity :mild)
  (def error-message (str error-message "MILDLY INCONVENIENCED!"))
  (def error-message (str error-message "DOOOOOOOMED!")))

; More Clojure:
(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOMED!")))

(error-message :mild)

; All of Clojure data structures are immutable.

; Map literal:
{:first-name "Charlie"
 :last-name "McFishwich"}

; Hash map function
(hash-map :a 1 :b 2)

; Look up values in a map using `get`
; Below, we grab the value of 'b'
(get {:a 0 :b 1} :b)

; `get` will return `nil` if it does not find your value.
; But we can define a default value.
(get {:a 0 :b 1} :c "unicorns?") ; Try to 'c', returns 'unicorns?'

; Treat map like a function, with key as argument.
({:name "The Human Coffeepot"} :name)

; The `:` prefix, is for keywords.
; We used them as keys in maps previously.
; Keywords can be used as functions that look up the corresponding value in a data structure.
(:a {:a 1 :b 2 :c 3})
; => 1

; We can use `get` for vectors/lists:
(get [3 2 1] 0)
; => 3

; To add to a vector (append), use `conj`
(conj [1 2 3] 4)

; There is no `get` for lists though
; List literals start with a single quote
'(1 2 3 4)
; => (1 2 3 4)
; To get an element from a list we use `nth`
(nth '(:a :b :c) 0)
; From a list of :a :b :c, get 0 index (nth)
; This is slower than get/vectors since nth has to traverse the full list.

; Lists can have any type
(list 1 "two" {3 4})
; Add/append with `conj` as before with vectors.

; Hash-sets
#{"kurt vonnegut" 20 :icicle}
(hash-set 1 1 2 2)

(set [3 3 3 4 4])

; Huge focus in Clojure on using its data structures vs writing your own.
; For now, keep an eye out for the ways that you gain code reusability by sticking to basic data structures.
