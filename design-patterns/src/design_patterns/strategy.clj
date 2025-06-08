(ns design-patterns.strategy)

;; As per strategy pattern process-payment is a higher-order function
;; that takes a payment method function and an amount, and applies the method
;; to process the payment. This allows for different payment methods to be
;; passed in dynamically, adhering to the strategy pattern.

;; Note the use of `apply` to allow for variable arguments. I had missed it earlier.
(defn process-payment
  [payment-method amount & args]
  (apply payment-method amount args))

;; Define different payment methods as strategies
;; You can add more payment methods as needed
(defn credit-card-payment
  [amount {:keys [card-number expiration-date]}]
  {:type :credit-card
   :amount amount
   :card-number card-number
   :expiration-date expiration-date})

(defn paypal-payment
  [amount {:keys [email]}]
  {:type :paypal
   :amount amount 
   :email email})

;; Extend the payment-methods map to include more payment methods
;; Each key is a symbol representing the payment type, and the value is the
;; corresponding function that implements the payment method.
;; This allows for easy addition of new payment methods without changing the
;; existing code structure.
(def payment-methods
  {:credit-card credit-card-payment
   :paypal paypal-payment})

;; As per strategy pattern, get-payment-mthod is a selector function
;; that returns the appropriate payment method function based on the payment type
;; and execute-payment is the context function that uses the selected method
;; to process the payment.
(defn get-payment-method
  [payment-type]
  (get payment-methods payment-type))

(defn execute-payment
  [payment-type amount & args]
  (process-payment (get-payment-method payment-type) amount args))

