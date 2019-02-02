(ns acme.app.core
  (:require ["react" :as react]
            ["react-dom" :as react-dom]
            [hx.react :as hx]
            [acme.app.counter :as counter]))

(def log js/console.log)

(defn get-element [selector]
  (.querySelector js/document selector))

(defn get-elements [selector]
  (.querySelectorAll js/document selector))

(defn el [] (react/createElement "div" nil "hello world 3"))


(hx/defnc sub-component [{:keys [name]}]
  [:h1 (str "hello " name "!")])

(hx/defnc my-component []
  [:div "Hello, React 5"
   [sub-component {:name "test"}]
   [counter/counter]
   [counter/drag-example]])



(defn start []
  (.reload (.-location js/window)))

(defn ^:dev/after-load init []
  (react-dom/render (hx/f [my-component]) (get-element "#app")))