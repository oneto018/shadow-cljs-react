(ns acme.app.reagent-component
  (:require [reagent.core :as r]))

(defn get-element [selector]
  (.querySelector js/document selector))


(defn counter-isolated []
  (let [count (r/atom 0)]
    (fn []
      [:div
       [:h3 "count:" @count]
       [:button {:on-click #(swap! count inc)}  "increment"]])))


(defonce count-global (r/atom 0))

(defn counter-polluted []
  [:div
   [:h3 "count:" @count-global]
   [:button {:on-click #(swap! count-global inc)}  "increment"]])

(defn simple-component []
  [:div
   [:p "I am an isolated counter component"]
   [counter-isolated]
   [:p "I am  counter component with namespace level state"]
   [counter-polluted]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])

(defn render-simple []
  (r/render [simple-component]
            (get-element "#reagent-app")))