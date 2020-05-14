
-- CSCE 314 [Sec. 501] Programming Languages Spring 2020 Hyunyoung Lee
-- Homework Assignment 4A: Exercise 12.7
-- This is an individual assignment.  Remembr the Aggie Honor Code!
-- You will earn total 40 points in this assignment.
-- Deadline: 10:00 p.m. Monday, March 2, 2020

-- Problem 1 (3 points)
-- Student Name: Jiarui Li
-- UIN: 827003805
-- GitHub

module Main where

import Test.HUnit
import System.Exit


-- Chapter 12 Exercise 7 (37 points)
data Expr a = Var a | Val Int | Add (Expr a) (Expr a)
              deriving Show

-- Val Int should be understood as Nothing in Maybe type. 
-- Whatever operations applied to it will not be effective. 

-- Var a: functions will be applied on a
-- Add (Expr a) (Expr a) is recursively defined

instance Functor Expr where
-- fmap :: (a -> b) -> Expr a -> Expr b
-- P1. [10 points] Give three definitions for fmap for Expr 
   fmap f (Var a) = Var (f a) -- works like Just in Maybe
   fmap f (Val b) = Val b     -- nothing is applied
   fmap f (Add a b) = Add (fmap f a) (fmap f b) -- recursively applied

instance Applicative Expr where
-- pure :: a -> Expr a
    pure a = Var a
-- <*> :: Expr (a -> b) -> Expr a -> Expr b
-- left operand is a funciton put in to the same context (Expr)
-- P2. [10 points] Give three definitions for (<*>) for Expr
{-
    _ <*> Val a = Val a
    (pure f) <*> (Var a) = Var (f a)
    (pure f) <*> (Add a b) = Add (fmap f a) (fmap f b)
-}
    (Val f) <*> _ = Val f
    (Var f) <*> a = fmap f a
    (Add a b) <*> x = Add (a <*> x) (b <*> x)



instance Monad Expr where
-- (>>=) :: Expr a -> (a -> Expr b) -> Expr b
-- P3. [10 points] Give three definitions for (>>=) for Expr
   (Val a) >>= _ = Val a
   (Var a) >>= f = f a
   (Add a b) >>= f = Add (a >>= f) (b >>= f)
   return = pure
   -- return is to put the value in context

-- P4. [7 points] Using the Expr object e1x and the first definition
-- of function g (see below), explain what the (>>=) operator for
-- the Expr type does when you do 
-- > e1x >>= g
-- in GHCi. Be as specific as possible!
{- Write your answer here.
	What operator (>>=) does in this case is taking the actual parameter
	of an Expr out and bind or apply the given function onto the parameter, 
	then put the return value back in the container, aka packing them back to an expression.

	For example, 
	e1x >>= g = 
		Add ((Val 1) >>= g) ((Var 'x') >>= g)
		= Add (Val 1) (g 'x')
		= Add (Val 1) (Val 2)
-}

-- Expr objects 
e1x = Add (Val 1) (Var 'x')
e2y = Add (Val 2) (Var 'y')
ez3 = Add (Var 'z') (Val 3) 
exyz = Add (Add (Var 'x') (Var 'y')) (Add (Var 'z') (Var 'y'))

-- Function g definitions
g 'x' = Val 2   -- definition 1
g 'y' = Val 3
g 'z' = Val 4

-- A simplest eval function for tests below
eval :: Expr a -> Int
eval (Val n) = n
eval (Add a b) = eval a + eval b

-- (TestCase assertEqual "e bind g" (e >>= g) e_g)
myTestList = 
  TestList [
     "test 1" ~: eval (e1x >>= g)  ~?= 3
    ,"test 2" ~: eval (e2y >>= g)  ~?= 5
    ,"test 3" ~: eval (ez3 >>= g)  ~?= 7
    ,"test 4" ~: eval (exyz >>= g) ~?= 12
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

