(+ 1 2)
(* 3 4)
(- 10 2 3)
(/ 20 2)
(+ 1 (* 2 3))

(*(+ 2 5) 2)


(setq edad 25)
(print edad) 

(defun clasificar-numero (x)
  (cond
    ((> x 0) "Positivo")
    ((< x 0) "Negativo")
    (t "Cero")))

(print (clasificar-numero 10))   
(print (clasificar-numero -5))   
(print (clasificar-numero 0))    