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
    ((> x 0) "positivo")
    ((< x 0) "negativo")
    ( "Cero")))

(print (clasificar-numero (+ negativo 2) ))
(print(- 3 10))
(setq nombre "Diego")
(print nombre) 


(defun fibonacci (n)
  (cond
    ((= n 0) 0)
    ((= n 1) 1)
    ((+ (fibonacci (- n 1)) (fibonacci (- n 2))))))

(print (fibonacci 10))
(print (fibonacci 12))

(print (atom 10))
(print (atom "hola"))

(print (atom(list 1 2 3 4 5)))

(print (list 1 2 3 4 5))