(ns design-patterns.singleton)

(defn create-instance
  "Returns a singleton instance"
  []
  (println "Creating a new instance")
  {:instanceid (java.util.UUID/randomUUID)
   :instance-data "This is the singleton instance"})

(defonce singleton-instance (atom nil))

(defn get-instance-old
  "Returns the singleton instance, creating it if it doesn't exist"
  []
  (if (nil? @singleton-instance)
    (reset! singleton-instance (create-instance)))
  @singleton-instance)

(defn get-instance 
  "Returns the singleton instance, thread-safe, creating it if doesn's exist"
  []
  (swap! singleton-instance
         (fn [instance]
           (or instance (create-instance)))))