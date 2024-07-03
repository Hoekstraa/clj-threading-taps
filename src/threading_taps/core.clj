(ns threading-taps.core)

(defn ->>tap
  "Kestrel-type tap. Will apply `x` to `fn` and return `x`."
  [fn x]
  (fn x)
  x)

(defn ->tap
  "Kestrel-type tap. Will apply `x` to `fn` and return `x`."
  [x fn]
  (fn x)
  x)
