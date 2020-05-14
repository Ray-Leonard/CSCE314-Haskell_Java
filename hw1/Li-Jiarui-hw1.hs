
-- CSCE 314 [Section 501] Programming Languages Spring 2020
-- Homework Assignment 1

-- Problem 1 (5 points)
-- This is head comment (single line comment should be preceded by two dashes)
-- Student Name: Jiarui Li
-- UIN: 827003805
-- (N/A)

module Main where

import Test.HUnit
import System.Exit


-- Example:
-- Write a recursive function mySum that sums all the numbers
-- in a list without using the prelude function sum.
mySum :: [Int] -> Int  -- type signature of mySum. mySum accepts a list of Ints
                       -- as its argument and returns an Int
mySum []     = 0  -- def 1
mySum (x:xs) = x + mySum xs -- def 2

{- Block comment over multiple lines is enclosed within {- and -}
Explanation:
The type of mySum tells us that mySum takes a list of Ints as an argument
and returns an Int that is the sum of all the Ints in the argument list.

The def 1 of mySum is the base case of the recursion, that is,
the argument list is empty, for which case the function value is 
defined as zero (summation identity).

The def 2 is when the argument list contains at least one element, 
namely x, in which case the function is defined as the sum of x 
and the result of the recursive call of mySum applied to the rest of 
the argument list, namely xs.
-}


-- Problem 2 (10 points)
lucas :: Int -> Int
lucas 0 = 2
lucas 1 = 1
lucas n = lucas (n-1) + lucas (n-2)


-- Problem 3 (5+10 = 15 points)
qsort1 :: Ord a => [a] -> [a]
---- Question 3.1 (5 points)
qsort1 [] = []
qsort1 (x:xs) = qsort1 larger ++ [x] ++ qsort1 smaller
               where
                larger = [a | a <- xs, a > x]
                smaller = [b | b <- xs, b <= x]


---- Question 3.2 (10 points)
{- Write your answer for Question 3.2 within this block comment.

  qsort1 [3,2,3,1,4]
= qsort1 [4] ++ [3] ++ qsort1 [2,3,1]
= (qsort1 [] ++ [4] ++ qsort1 []) ++ [3] ++ (qsort1 [3] + [2] + qsort1 [1])
= ([] ++ [4] ++ []) ++ [3] ++ ((qsort1 [] ++ [3] ++ qsort1 []) ++ [2] ++ (qsort1 [] ++ [1] ++ qsort1 []))
= ([] ++ [4] ++ []) ++ [3] ++ (([] ++ [3] ++ []) ++ [2] ++ ([] ++ [1] ++ []))
= [4] ++ [3] ++ [3] ++ [2] ++ [1]
= [4,3,3,2,1]
  
  qsort1 has been recursively called 10 times.
-}


-- Problem 4 (Chapter 5, Exercise 9) (10+10=20 points)
scalarproduct :: [Int] -> [Int] -> Int
---- Question 4.1 (10 points)
scalarproduct xs ys = sum [x * y | (x,y) <- zip xs ys]

{-
-- Done not using zip - meaning lists can't have different lengths
scalarproduct [] [] = 0
scalarproduct (x:xs) (y:ys) = x * y + scalarproduct xs ys
-}

---- Question 4.2 (10 points)
{- Write your answer for Question 4.2 within this block comment.
 When scalarproduct [1,2,3] [4..] is invoked,
 zip [1,2,3] [4..] will be invoked since the program evaluates the generator first.
 zip function produces a new list by pairing successive elements from two existing lists
 until either or both lists are exhausted:
 zip [1,2,3] [4..] = 
  [(1,4), (2,5), (3,6)] and it will stop here since the first list is exhausted.
 Then the list comprehension generator will generate the list [1 * 4, 2 * 5, 3 * 6] = 
  [4, 10, 18]
 At last sum [4, 10, 18] will have the result of 4 + 10 + 18 = 32
-}



-- Problem 5 (Chapter 6, Exercise 7) (10 points)
merge :: Ord a => [a] -> [a] -> [a]
merge xs [] = xs
merge [] ys = ys
merge (x:xs) (y:ys) = if x < y
                      then x:(merge xs (y:ys))
                      else y:(merge ys (x:xs))



-- Problem 6 (Chapter 6, Exercise 8) (8+7=15 points)
halve :: [a] -> ([a], [a])  -- 7 points
halve xs = splitAt (length xs `div` 2) xs


msort :: Ord a => [a] -> [a]  -- 8 points
msort [] = []
msort [x] = [x]
msort xs = merge (msort left) (msort right)
            where 
              (left, right) = halve xs



-- Problem 7 (10 points)
isElem :: Eq a => a -> [a] -> Bool
isElem e [] = False
isElem e (x:xs) = if e == x
                  then True
                  else isElem e xs


type Set a = [a]

-- Problem 8 (15 points)
toSet :: Eq a => [a] -> Set a
toSet [] = []
toSet (x:xs) = if isElem x xs
              then toSet xs
              else x:toSet xs



myTestList = 
  TestList [

      "lucas 1" ~: lucas 0 ~=? 2
    , "lucas 2" ~: lucas 1 ~=? 1    
    , "lucas 3" ~: lucas 4 ~=? 7
    
    , "qsort1 1" ~: qsort1 [3, 2, 5, 1, 6] ~=? [6,5,3,2,1]
    , "qsort1 2" ~: qsort1 "howdy" ~=? "ywohd"
    
    , "scalarproduct 1" ~: scalarproduct [4,5,6] [1,2,3] ~=? 32
    , "scalarproduct 2" ~: scalarproduct [2,3] [1,-1] ~=? -1
    , "scalarproduct 3" ~: scalarproduct [1..10] [1..5] ~=? 55

    , "merge 1" ~: merge "EGG" "ABCDEFGH" ~=? "ABCDEEFGGGH" 
    , "merge 2" ~: merge "Hello" "e" ~=? "Heello"

    , "halve 1" ~: halve "" ~=? ("","")
    , "halve 2" ~: halve "halves" ~=? ("hal","ves")
    , "halve 21" ~: halve "halve" ~=? ("ha","lve")

    , "msort 1" ~: msort "Howdy all!" ~=? " !Hadllowy"
    , "msort 2" ~: msort "" ~=? ""
    , "msort 3" ~: msort "Mississippi" ~=? "Miiiippssss"

    , "isElem 1" ~: (isElem 'h' "happy") ~=? True
    , "isElem 2" ~: (isElem 'o' "happy") ~=? False

    , "mkSet 1" ~: length (toSet "aardvark") ~=? 5
    , "mkSet 2" ~: length (toSet "Bart") ~=? 4

    ]

main = do c <- runTestTT myTestList
          putStrLn $ show c
          let errs = errors c
              fails = failures c
          exitWith (codeGet errs fails)
          
codeGet errs fails
 | fails > 0       = ExitFailure 2
 | errs > 0        = ExitFailure 1
 | otherwise       = ExitSuccess

