# Scrabble

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

Interactive graphic programming language heavily inspired by 
[Scratch](https://scratch.mit.edu).
This work is licensed under a
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/), 
and aims purely to be used with the purpose of teaching in the context of the course 
_CC3002 Metodologías de Diseño y programación_ of the 
[_Computer Sciences Department (DCC)_](https://www.dcc.uchile.cl) of the 
_University of Chile_.

---

This project is based on _Scratch_ which is a programming language that uses block
stacking programming for the structure of a program. The goal for this project is
create a simplified version of this language.

# Functionality

The variables can be any of this five options:
* *Int*: needs an _Integer_ (Z) to be created (Java Integer)
* *Float*: needs a _Rational number_ (Q) to be created (Java Double).
* *Bool*: represents the values of _True_ and _False_. That's why it needs a Java Boolean for it's construction
* *String*: represents _letters_, _words_ and _sentences_. So it needs a Java String to be created.
* *Binary*: represents _Binary numbers_ with 32 bits. It's created with a Java String that contains only ones and zeros.

The classes were created based on the Java type that a variable needs for its creation.

We have the abstract class BuiltWithString which has two child class: *Scrabble_String* and
*Scrabble_Binary*. Those are the variables who need a Java String to be build. The other types
don't have a Java primitive type in common, that's why they don't have a parent (abstract) class.
The classes are *Scrabble_Int*, *Scrabble_Float* and *Scrabble_Bool*

### Transformations

All the variables can be transformed into other types of variables, but they can't 
transform into all of them. The next table contains the transformations that
every kind of variable is able to do.

|  | String | Bool | Float | Int | Binary |
| --- | --- | --- | --- | --- | --- |
| String | O | X | X | X | X |
| Bool | O | O | X | X | X |
| Float | O | X | O | X | X |
| Int | O | X | O | O | O |
| Binary | O | X | O | O | O |

If we put the table into words, the variables can do the next transformations:

* *String*: can transform into a String
* *Bool*: can transform into a String and Bool
* *Float*: can transform into a String and Float
* *Int*: can transform into a String, Float, Int and Binary
* *Binary*: can transform into a String, Float, Int and Binary

### Operations

The operations can be divided into two groups:
* _Logical_: and (^), or (v) , not
* _Arithmetic_: +, -, *, /

The classification of the classes based on the operation are:
* _Scrabble Logical_: *Scrabble_Binary* and *Scrabble_Boolean*
* _Scrabble Arithmetical_: *Scrabble_Binary*, *Scrabble_Int* and *Scrabble_Float*

This classification is represented in the program with two interfaces: *IScrabbleLogical*
and *IScrabbleArithmetic*.


The relation between variables and binary operations is shown in the next table:

|  | String | Bool | Float | Int | Binary |
| --- | --- | --- | --- | --- | --- |
| String | + | + | + | + | + |
| Bool |  | ^, v |  |  | ^, v |
| Float |  |  | +, -, *, / | +, -, *, / | +, -, *, / |
| Int |  |  | +, -, *, / | +, -, *, / | +, -, *, / |
| Binary |  | ^, v |  | +, -, *, / | +, -, *, / |

The operations aren't bidirectional, so the results of the operations considering the
Scrabble type as the first operand are the next ones:
- _Scrabble_String_: the result is always a *Scrabble_String*
- _Scrabble_Bool_:
    * with *Scrabble_Bool*: *Scrabble_Bool*
    * with *Scrabble_Binary*: *Scrabble_Binary*
- _Scrabble_Float_:
    * with *Scrabble_Float*: *Scrabble_Float*
    * with *Scrabble_Int*: *Scrabble_Float*
    * with *Scrabble_Binary*: *Scrabble_Float*
- _Scrabble_Int_: 
    * with *Scrabble_Float*: *Scrabble_Float*
    * with *Scrabble_Int*: *Scrabble_Int*
    * with *Scrabble_Binary*: *Scrabble_Int*
- _Scrabble_Binary_:
    * with *Scrabble_Bool*: *Scrabble_Binary*
    * with *Scrabble_Int*: *Scrabble_Binary*
    * with *Scrabble_Binary*: *Scrabble_Binary*

In the case of the unary operation, which in this case is 'not', the result is always
the same type. Thus, if you apply ```not``` to a *Scrabble_Binary* the result is a 
*Scrabble_Binary* and if you do it with a *Scrabble_Bool*, 
the result is a *Scrabble_Bool*.

### Operation Tree

If we want to do multiple operations, the best way to do it is with operation trees.
To build those trees, you have to set your root -which is an operation-, then build
your left branch and right branch *in that order* because the tree does the operations
*inorder* (left-root-right).

The operations follow the same rules as the Scrabble Types, and you can also do
transformations to the nodes, just like the Scrabble Types. You have to be careful
with the order of the operations, because there are some of them that are invalid.

### Memory

Creating Scrabble Types can be very memory expensive, so that's why we implemented
Factories for each kind of Scrabble Type. So every time that you create a new variable,
that variable is stored in a HashMap that contains other variables only if that variable
hasn't been created yet. If you create a new variable that's already in the HashMap, the
Factory returns the variable with the same value that was stored in it.

## Graphic Interface (*halfway implemented*)

### How to use

First, you have to create a variable, selecting an option of Scrabble type
then writing a valid value for the variable. After that, you have to press the ```Create```
button. You can create one or two variables depending on the operation that you want to apply.

The next step is apply an operation, you have to select an operation then press the ```Apply```
button.

You can repeat the first two steps as many times as you want.

The final step is press the ```Calculate``` button, which will calculate the value of the
operations that you've entered before. At the same time, the tree with all the operations
will show up.

You can keep doing operations, but if you want to erase all the history of calculations, you
have to press the ```Clear``` button.

All the functionalities of the interface are not implemented.

