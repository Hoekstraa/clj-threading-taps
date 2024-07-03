(ns threading-taps.core-test
  (:require [clojure.test :refer :all]
            [threading-taps.core :refer :all]))

(defn function-with-non-nil-return
  "Basic func for testing purposes"
  [& _]
  "TEST")

(deftest ->tap-test
  (testing "tap function gets called"
    (let [fake-io (atom nil)]
      (-> "Hello, "
          (str "world")
          (->tap (partial reset! fake-io))
          (str "!"))
      (is (= "Hello, world" @fake-io))))

  (testing "tap function doesn't affect output"
    (is (= "Hello, world!"
           (-> "Hello, "
               (str "world")
               (->tap function-with-non-nil-return)
               (str "!"))))))

(deftest ->>tap-test
  (testing "tap function gets called"
    (let [fake-io (atom nil)]
      (->> "!"
           (str "world")
           (->>tap (partial reset! fake-io))
           (str "Hello, "))
      (is (= "world!" @fake-io))))

  (testing "tap function doesn't affect output"
    (is (= "Hello, world!"
           (->> "!"
                (str "world")
                (->>tap function-with-non-nil-return)
                (str "Hello, "))))))
