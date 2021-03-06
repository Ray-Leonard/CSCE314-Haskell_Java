
-- CSCE 314 [Section 501] Programming Languages Spring 2020
-- Homework Assignment 2

-- Problem 1 (5 points)
-- This is head comment (single line comment should be preceded by two dashes)
-- Student Name: Jiarui Li
-- UIN: 827003805
-- (ACKNOWLEDGE ANY HELP RECEIVED HERE)

module Main where

import Prelude hiding ((&&))

import Test.HUnit
import System.Exit
import Data.Char


-- Problem 2 (5 points) Chapter 4, Exercise 5
(&&) :: Bool -> Bool -> Bool
(&&) a b = if a == False then False else
              if b == True then True else False

-- Problem 3 (10+10 = 20 points) Chapter 4, Exercise 8
luhnDouble :: Int -> Int  -- 10 points
luhnDouble n | n*2 < 9 = n*2
             | otherwise = n*2-9

luhn :: Int -> Int -> Int -> Int -> Bool  -- 10 points
luhn a b c d = mod (d + luhnDouble c + b + luhnDouble a) 10 == 0


-- Problem 4 (10 points) Chapter 5, Exercise 6
factors :: Int -> [Int]
factors n = [x | x <- [1..n-1], n `mod` x == 0]

perfects :: Int -> [Int]
perfects n = [x | x <- [1..n], sum (factors x) == x]


-- Problem 5 (7+7+6 = 20 points) Chapter 6, Exercise 5
{- Write your answer in this block comment neatly and clearly.

1. length [1,2,3]
= length (1:2:3:[])
= 1 + length (2:3:[])
= 1 + (1 + length (3:[]))
= 1 + (1 + (1 + length []))
= 1 + (1 + (1 + 0))
= 3

2. drop 3 [1,2,3,4,5]
= drop 3 (1:2:3:4:5:[])
= drop 2 (2:3:4:5:[])
= drop 1 (3:4:5:[])
= drop 0 (4:5:[])
= [4,5]

3. init [1,2,3]
= init (1:2:3:[])
= 1 : init (2:3:[])
= 1 : 2 : init (3:[])
= 1 : 2 : []
= [1,2]
-}



-- Problem 6 (8+7=15 points)
halve :: [a] -> ([a], [a])
halve xs = splitAt (length xs `div` 2) xs

mergeBy :: (a -> a -> Bool) -> [a] -> [a] -> [a]  -- 8 points
mergeBy _ xs [] = xs
mergeBy _ [] ys = ys
mergeBy f (x:xs) (y:ys) = if f x y
                      then x:(mergeBy f xs (y:ys))
                      else y:(mergeBy f ys (x:xs))

msortBy :: (a -> a -> Bool) -> [a] -> [a]  -- 7 points
msortBy _ [] = []
msortBy _ [x] = [x]
msortBy f xs = mergeBy f (msortBy f left) (msortBy f right)
                where (left, right) = halve xs


-- Problem 7 (10+5 = 15 points) Chapter 7, Exercise 9
altMap :: (a -> b) -> (a -> b) -> [a] -> [b]  -- 10 points
altMap f g [] = []
altMap f g [x] = [f x]
altMap f g (x:y:xs) = f x : g y : altMap f g xs

{- (5 points)
   Explain how your altMap works when it is applied as below.
   > altMap (*2) (`div` 2) [0..6]
= altMap (*2) (`div` 2) (0:1:2:3:4:5:6:[])
= (0 * 2) : (1 `div` 2) : altMap (*2) (`div` 2) (2:3:4:5:6:[])
= 0:0: (2 * 2) : (3 `div` 2) : altMap (*2) (`div` 2) (4:5:6:[])
= 0:0:4:1: (4 * 2) : (5 `div` 2) : altMap (*2) (`div` 2) (6:[])
= 0:0:4:1:8:2:(6*2):[]
= [0,0,4,1,8,2,12]
-}


-- Problem 8 (10 points)
concatenateAndUpcaseOddLengthStrings :: [String] -> String
concatenateAndUpcaseOddLengthStrings xs = foldr (++) [] [map toUpper x | x <- (filter (odd . length) xs)]



myTestList = 
  TestList [
  
      "&& 1" ~: (&&) True True ~=? True
    , "&& 2" ~: (&&) True False ~=? False    
    , "&& 3" ~: (&&) False True ~=? False
    , "&& 4" ~: (&&) False False ~=? False
    
    , "luhnDouble 1" ~: luhnDouble 3 ~=? 6
    , "luhnDouble 2" ~: luhnDouble 6 ~=? 3
    , "luhnDouble 3" ~: luhnDouble 5 ~=? 1

    , "luhn 1" ~: luhn 1 7 8 4 ~=? True
    , "luhn 2" ~: luhn 4 7 8 3 ~=? False
    
    , "perfects 1" ~: perfects 500 ~=? [6,28,496]

    , "mergeBy 1" ~: mergeBy (>) "FED" "GBA" ~=? "GFEDBA"
    , "mergeBy 2" ~: mergeBy (<) "Howdy" "Maui" ~=? "HMaouiwdy"
      
    , "msortBy 1" ~: msortBy (<) "gig 'em" ~=? " 'eggim" 
    , "msortBy 2" ~: msortBy (>) "Jack be nimble" ~=? "nmlkieecbbaJ  "
    , "msortBy 3" ~: msortBy (<) "" ~=? ""

    , "altMap 1" ~: altMap (* 10) (* 100) [1,2,3,4,5] ~=? [10,200,30,400,50]
    , "altMap 2" ~: and (altMap even odd [1..10]) ~=? False
    , "altMap 3" ~: altMap toLower toUpper "Haskell IS fun!" ~=? "hAsKeLl iS FuN!"

    , "concatenateAndUpcaseOddLengthStrings" ~:
         concatenateAndUpcaseOddLengthStrings ["here's ", "an ", "a ", "example"] ~=? "HERE'S AN EXAMPLE" 

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

