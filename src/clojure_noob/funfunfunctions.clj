(ns clojure-noob.core
  (:gen-class))

(+ 1 2 3 4)
(* 1 2 3 4)
(first [1 2 3 4])

((or + -) 1 2 3)
((and (= 1 1) +) 1 2 3) ; 1 equal 1 is truthy, returns '+', funcs 1 2 3
((first [+ 0]) 1 2 3)
; => 6 because + is the first truthy value

; Alone they are not functions:
(1 2 3 4)
("test" 1 2 3)
; => Will result in errors.

; Function flexibility doesn’t end with the function expression!
; Syntactically, functions can take any expressions as arguments—including other functions.
; Functions that can either take a function as an argument or return a function are called higher-order functions.
; Programming languages with higher-order functions are said to support first-class functions because you can treat functions as values in the same way you treat more familiar data types like numbers and vectors.

; map creates a new list by applying a function to each member of a collection. 
; Here, the inc function increments a number by 1:
(inc 1.1)
; => 2.1 (inc+=1

(map inc [0 1 2 3])
; => (1 2 3 4))
; Map was passed a VECTOR but it returns as a LIST. (this is ok and expected...)

; Clojure evaluates all function arguments recursively before passing them to the function.
; Here’s how Clojure would evaluate a function call whose arguments are also function calls:
(+ (inc 199) (/ 100 (- 7 2)))
(+ 200 (/ 100 (- 7 2))) ; evaluated "(inc 199)"
(+ 200 (/ 100 5)) ; evaluated (- 7 2)
(+ 200 20) ; evaluated (/ 100 5)
220 ; final evaluation
; The function call kicks off the evaluation process, and all subforms are evaluated before applying the + function.


(if good-mood
  (tweet walking-on-sunshine-lyrics)
  (tweet mopey-country-song-lyrics))

; Function body
; defn
; Function name
; A docstring describing the function (optional)
; Parameters listed in brackets
; Function body

(defn too-enthusiastic
  "Return a cheer that is a bit too enthusiastic"
  [name]
  (str "OH MY GOD " name " WOO!"))

(too-enthusiastic "Bob")
; => "OH MY GOD Bob WOO!"

(defn no-params
  []
  "I take no parameters!")

(defn one-param
  [x]
  (str "I take one parameter: " x))

(defn two-params
  [x y]
  (str "Two parameters! That's nothing! Pah! I will smoosh them "
       "together to spite you! " x y))

; Arity is the number of arguments a function takes. 0 arity, 1 arity, and so on.
; Clojure supports arity overloading, diff. function body will run based on arity.
(defn multi-arity
  ;; 3-arity arguments and body
  ([first-arg second-arg third-arg]
   (do-things first-arg second-arg third-arg))
  ;; 2-arity
  ([first-arg second-arg]
   (do-things first-arg second-arg))
  ;; 1-arity
  ([first-arg]
   (do-things first-arg)))

; Can use arity to do default parameters.
(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name chop-type]
   (str "I " chop-type " chop " name "! Take that!"))
  ([name]
   (x-chop name "karate"))) ; Karate becomes the default chop-type if not passed.

(x-chop "Kanye West")
(x-chop "Kanye West" "slap")


; Clojure also allows you to define variable-arity functions by including a rest parameter
; Using &
(defn codger-communication ; func name
  [whippersnapper] ; func args
  (str "Get off my lawn, " whippersnapper "!!!")) ; func body

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(codger "Billy" "Anne-Marie" "The Incredible Bulk")
; => ("Get off my lawn, Billy!!!"
;     "Get off my lawn, Anne-Marie!!!"
;     "Get off my lawn, The Incredible Bulk!!!")

; Parameters got treated as a list, &rest has to come last as argument.
(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

(favorite-things "Doreen" "gum" "shoes" "kara-te") ; gum shoes kara-te are ..rest/things

; Destructuring
;; Return the first element of a collection
(defn my-first
  [[first-thing]] ; Notice that first-thing is within a vector
  first-thing)

(my-first ["oven" "bike" "war-axe"])
; => "oven"

; Wrap args in a vector, then we take positions as keys
; Everything after first/second choice are "rested" as unimportant
(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))
(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})
; => Treasure lat: 100
; => Treasure lng: 50

; Break keys out of a map, faster than ^
(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

; Retain the original map/DS by using `as`.
(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))

  ;; One would assume that this would put in new coordinates for your ship
  (steer-ship! treasure-location))

; Function body in Clojure can contain forms of any kind.
; Automatically returns the last form evaluated.
(defn illustrative-function
  []
  (+ 1 304)
  30
  "joe")
; Will return "joe" since it's the last evaluated form.

; All functions in Clojure are equal. there is no privileges between built-in and our own.

; Anonymous Functions
; Like other languages, functions don't need names.
(map (fn [name] (str "Hi, " name))
     ["Darth Vader", "Jiro"])

((fn [x] (* x 3)) 8)
; => 24

; `fn` and `defn` behaviour identically.
; Can associate anon. function with a name
(def my-special-multiplier (fn [x] (* x 3)))
(my-special-multiplier 12)
; => 36

; Also anonymous
(#(* % 3) 8)

; %1 %2 like string formatting in python or whatever
(#(str %1 " and " %2) "cornbread" "butter beans")

; Using rest with format
(#(identity %&) 1 "blarg" :yip)
; => (1 "blarg" :yip)

; Returning Functions
; Functions can return other functions, which are closures.
; They can access all the variables that were in scope when the function was created
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)
; => 10
