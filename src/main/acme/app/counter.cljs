(ns acme.app.counter
  (:require ["react" :as react]
            ["react-with-gesture" :as gesture]
            ["react-spring/hooks" :as react-spring]
            [goog.object :as gobj]
            [hx.react :as hx]))


(def log js/console.log)


(def use-state react/useState)

(hx/defnc counter []
  (let [[count set-count!] (use-state 0)]
    [:div
     [:h1 (str "count2: " count)]
     [:button {:on-click #(set-count! (inc count))} "plus + "]
     [:button {:on-click #(set-count! (dec count))} "minus - "]]))

(hx/defnc counter-alt []
  (let [[count set-count!] (use-state {:count 0})]
    [:div
     [:h1 (str "count: " (:count count))]
     [:button {:on-click #(set-count! (update count :count inc))} "plus + "]
     [:button {:on-click #(set-count! (update count :count dec))} "minus - "]]))


;;from Mike Fikes https://stackoverflow.com/a/49848352
(extend-type object
  ILookup
  (-lookup
    ([o k]
     (gobj/get o (name k)))
    ([o k not-found]
     (gobj/get o (name k) not-found))))

(comment (let [{:keys [a b]} #js{:a 10, :b 20}]
           (print a)
           (print b)))

(def animated react-spring/animated)

(defn get-bind [bind]
  (let [{:keys [onMouseDown,onTouchStart]} (bind)]
    {:on-mouse-down onMouseDown :on-touch-start onTouchStart}))

(hx/defnc drag-example []
  (let [[bind {:keys [delta down]}] (gesture/useGesture)
        {:keys [x]} (react-spring/useSpring #js{:x (if down (first delta) 0)})
        style  {:transform (x.interpolate  #(str "translateX(" % "px)"))}
        props (-> (get-bind bind) (assoc :style style))]
    [:div
     [:h2 "try to pull from the sides"]
     [animated.button props "pull me"]]))