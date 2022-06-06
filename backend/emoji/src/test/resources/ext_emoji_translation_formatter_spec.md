---
title: Emoji Extension Spec
author: Vladimir Schneider
version: 0.1
date: '2016-06-06'
license: '[CC-BY-SA 4.0](http://creativecommons.org/licenses/by-sa/4.0/)'
...

---

## Emoji

Converts :warning: to its emoji image

No spaces between markers

```````````````````````````````` example Emoji: 1
# some leading text 
more text :warning : more text
.
# SomeE LeEaADiING teEXt

moReE teEXt :WaARNiING : moReE teEXt
````````````````````````````````


No spaces between markers

```````````````````````````````` example Emoji: 2
# some leading text 
more text : warning: more text
.
# SomeE LeEaADiING teEXt

moReE teEXt : WaARNiING: moReE teEXt
````````````````````````````````


No spaces between markers

```````````````````````````````` example Emoji: 3
# some leading text 
more text :warning
: more text
.
# SomeE LeEaADiING teEXt

moReE teEXt :WaARNiING
: moReE teEXt
````````````````````````````````


No spaces between markers

```````````````````````````````` example Emoji: 4
# some leading text 
more text :
warning: more text
.
# SomeE LeEaADiING teEXt

moReE teEXt :
WaARNiING: moReE teEXt
````````````````````````````````


Converts :warning: to its emoji image

```````````````````````````````` example Emoji: 5
# some leading text 
:warning:
.
# SomeE LeEaADiING teEXt

:warning:
````````````````````````````````


change size

```````````````````````````````` example(Emoji: 6) options(size)
# some leading text 
:warning:
.
# SomeE LeEaADiING teEXt

:warning:
````````````````````````````````


no size

```````````````````````````````` example(Emoji: 7) options(no-size)
# some leading text 
:warning:
.
# SomeE LeEaADiING teEXt

:warning:
````````````````````````````````


no align

```````````````````````````````` example(Emoji: 8) options(no-align)
# some leading text 
:warning:
.
# SomeE LeEaADiING teEXt

:warning:
````````````````````````````````


Should work in links

```````````````````````````````` example Emoji: 9
# some leading text 
[:warning:](/url)
.
# SomeE LeEaADiING teEXt

[:WaARNiING:](/url)
````````````````````````````````


Should work in links

```````````````````````````````` example Emoji: 10
# some leading text 
[:warning:](/url)
.
# SomeE LeEaADiING teEXt

[:WaARNiING:](/url)
````````````````````````````````


Unknown shortcuts are converted to text

```````````````````````````````` example Emoji: 11
# some leading text 
:warnings:
.
# SomeE LeEaADiING teEXt

:warnings:
````````````````````````````````


Unknown shortcuts are converted to text

```````````````````````````````` example Emoji: 12
# some leading text 
:basecamp:
.
# SomeE LeEaADiING teEXt

:basecamp:
````````````````````````````````


Unknown shortcuts are converted to text with inline emphasis parsing

```````````````````````````````` example Emoji: 13
# some leading text 
:**warnings**:
.
# SomeE LeEaADiING teEXt

:**warnings**:
````````````````````````````````


## Issue 168

# 168, Text with colons is incorrectly interpreted as an invalid emoji shortcut

```````````````````````````````` example 168, Text with colons is incorrectly interpreted as an invalid emoji shortcut: 1
На сервере выставлен пояс GMT 00:00. Оно всегда должно быть *"3:50 ночи"*, даже если
.
нА СЕРВЕРЕ ВЫСТАВЛЕН ПОЯС gmt 00:00. оНО ВСЕГДА ДОЛЖНО БЫТЬ *"3:50 НОЧИ"*, ДАЖЕ ЕСЛИ
````````````````````````````````


