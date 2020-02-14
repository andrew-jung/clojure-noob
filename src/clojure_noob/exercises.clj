; Use the str, vector, list, hash-map, and hash-set functions.
(defn say-hello
  "Quick string func to say hello!"
  [name]
  "Hello there, " name " !")

(defn vect-this
  "vector function??"
  [[first-cat second-cat third-cat &cats]]
  (println "My favorite cats: " first-cat " , " second-cat ", " third-cat)
  (println "Not these cats: " &cats))
(def myHungries = {:stomach 100})

; Write a function that takes a number and adds 100 to it.
(defn add-one-hunnit
  "add 100 to passed num"
  [some-num]
  (+ some-num 100))

(say-hello "Andrew")
(vect-this ["Jiro" "Jiji" "Redd" "Nomi" "Bi" "Mango"])
(add-one-hunnit 1)

(defn titleize
  [topic]
  (str topic " for the Brave and True"))
; Vector
(map titleize ["Hamsters" "Ragnarok"])
; List
(map titleize '("Empathy" "Decorating"))

(map #(titleize (second %)) {:uncomfortable-thing "Winking"})


; pause: @/core-functions-in-depth/
