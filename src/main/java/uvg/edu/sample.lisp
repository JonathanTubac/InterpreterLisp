(+ 1 2)
(* 3 4)
(- 10 2 3)
(/ 20 2)
(+ 1 (* 2 3))

(*(+ 2 5) 2)


(setq edad 25)
(setq negativo -2)
(print edad) 
(print negativo)
 

(defun cuadrado (x)
  (* x x))

(print (cuadrado 5))


(defun clasificar-numero (x)
  (cond
    ((> x 0) 2)
    ((< x 0) 1)
    (t "Cero")))

(print (clasificar-numero negativo))

(setq nombre "Juan")
(print nombre) 