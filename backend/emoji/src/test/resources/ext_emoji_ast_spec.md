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
<h1>some leading text</h1>
<p>more text :warning : more text</p>
.
Document[0, 51]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 51]
    Text[21, 51] chars:[21, 51, "more  …  text"]
````````````````````````````````


No spaces between markers

```````````````````````````````` example Emoji: 2
# some leading text 
more text : warning: more text
.
<h1>some leading text</h1>
<p>more text : warning: more text</p>
.
Document[0, 51]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 51]
    Text[21, 51] chars:[21, 51, "more  …  text"]
````````````````````````````````


No spaces between markers

```````````````````````````````` example Emoji: 3
# some leading text 
more text :warning
: more text
.
<h1>some leading text</h1>
<p>more text :warning
: more text</p>
.
Document[0, 51]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 51]
    Text[21, 39] chars:[21, 39, "more  … rning"]
    SoftLineBreak[39, 40]
    Text[40, 51] chars:[40, 51, ": mor …  text"]
````````````````````````````````


No spaces between markers

```````````````````````````````` example Emoji: 4
# some leading text 
more text :
warning: more text
.
<h1>some leading text</h1>
<p>more text :
warning: more text</p>
.
Document[0, 51]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 51]
    Text[21, 32] chars:[21, 32, "more  … ext :"]
    SoftLineBreak[32, 33]
    Text[33, 51] chars:[33, 51, "warni …  text"]
````````````````````````````````


Converts :warning: to its emoji image

```````````````````````````````` example Emoji: 5
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p><img src="/img/26a0-fe0f.svg" alt="Warning Sign" height="24" width="24" align="absmiddle" /></p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    EmojiNode[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


change size

```````````````````````````````` example(Emoji: 6) options(size)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p><img src="/img/26a0-fe0f.svg" alt="Warning Sign" height="40" width="40" align="absmiddle" /></p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    EmojiNode[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


no size

```````````````````````````````` example(Emoji: 7) options(no-size)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p><img src="/img/26a0-fe0f.svg" alt="Warning Sign" align="absmiddle" /></p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    EmojiNode[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


no align

```````````````````````````````` example(Emoji: 8) options(no-align)
# some leading text 
:warning:
.
<h1>some leading text</h1>
<p><img src="/img/26a0-fe0f.svg" alt="Warning Sign" height="24" width="24" /></p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    EmojiNode[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


Should work in links

```````````````````````````````` example Emoji: 9
# some leading text 
[:warning:](/url)
.
<h1>some leading text</h1>
<p><a href="/url"><img src="/img/26a0-fe0f.svg" alt="Warning Sign" height="24" width="24" align="absmiddle" /></a></p>
.
Document[0, 38]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 38]
    Link[21, 38] textOpen:[21, 22, "["] text:[22, 31, ":warning:"] textClose:[31, 32, "]"] linkOpen:[32, 33, "("] url:[33, 37, "/url"] pageRef:[33, 37, "/url"] linkClose:[37, 38, ")"]
      EmojiNode[22, 31] textOpen:[22, 23, ":"] text:[23, 30, "warning"] textClose:[30, 31, ":"]
        Text[23, 30] chars:[23, 30, "warning"]
````````````````````````````````


Should work in links

```````````````````````````````` example Emoji: 10
# some leading text 
[:warning:](/url)
.
<h1>some leading text</h1>
<p><a href="/url"><img src="/img/26a0-fe0f.svg" alt="Warning Sign" height="24" width="24" align="absmiddle" /></a></p>
.
Document[0, 38]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 38]
    Link[21, 38] textOpen:[21, 22, "["] text:[22, 31, ":warning:"] textClose:[31, 32, "]"] linkOpen:[32, 33, "("] url:[33, 37, "/url"] pageRef:[33, 37, "/url"] linkClose:[37, 38, ")"]
      EmojiNode[22, 31] textOpen:[22, 23, ":"] text:[23, 30, "warning"] textClose:[30, 31, ":"]
        Text[23, 30] chars:[23, 30, "warning"]
````````````````````````````````


Unknown shortcuts are converted to text

```````````````````````````````` example Emoji: 11
# some leading text 
:warnings:
.
<h1>some leading text</h1>
<p>:warnings:</p>
.
Document[0, 31]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 31]
    EmojiNode[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "warnings"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "warnings"]
````````````````````````````````


Unknown shortcuts are converted to text

```````````````````````````````` example Emoji: 12
# some leading text 
:basecamp:
.
<h1>some leading text</h1>
<p>:basecamp:</p>
.
Document[0, 31]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 31]
    EmojiNode[21, 31] textOpen:[21, 22, ":"] text:[22, 30, "basecamp"] textClose:[30, 31, ":"]
      Text[22, 30] chars:[22, 30, "basecamp"]
````````````````````````````````


Unknown shortcuts are converted to text with inline emphasis parsing

```````````````````````````````` example Emoji: 13
# some leading text 
:**warnings**:
.
<h1>some leading text</h1>
<p>:<strong>warnings</strong>:</p>
.
Document[0, 35]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 35]
    EmojiNode[21, 35] textOpen:[21, 22, ":"] text:[22, 34, "**warnings**"] textClose:[34, 35, ":"]
      StrongEmphasis[22, 34] textOpen:[22, 24, "**"] text:[24, 32, "warnings"] textClose:[32, 34, "**"]
        Text[24, 32] chars:[24, 32, "warnings"]
````````````````````````````````


## Source Position Attribute

```````````````````````````````` example(Source Position Attribute: 1) options(src-pos)
# some leading text 
:warning:
.
<h1 md-pos="2-19">some leading text</h1>
<p md-pos="21-30"><img src="/img/26a0-fe0f.svg" alt="Warning Sign" height="24" width="24" align="absmiddle" /></p>
.
Document[0, 30]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 30]
    EmojiNode[21, 30] textOpen:[21, 22, ":"] text:[22, 29, "warning"] textClose:[29, 30, ":"]
      Text[22, 29] chars:[22, 29, "warning"]
````````````````````````````````


```````````````````````````````` example(Source Position Attribute: 2) options(src-pos)
# some leading text 
[:warning:](/url)
.
<h1 md-pos="2-19">some leading text</h1>
<p md-pos="21-38"><a href="/url" md-pos="21-38"><img src="/img/26a0-fe0f.svg" alt="Warning Sign" height="24" width="24" align="absmiddle" /></a></p>
.
Document[0, 38]
  Heading[0, 19] textOpen:[0, 1, "#"] text:[2, 19, "some leading text"]
    Text[2, 19] chars:[2, 19, "some  …  text"]
  Paragraph[21, 38]
    Link[21, 38] textOpen:[21, 22, "["] text:[22, 31, ":warning:"] textClose:[31, 32, "]"] linkOpen:[32, 33, "("] url:[33, 37, "/url"] pageRef:[33, 37, "/url"] linkClose:[37, 38, ")"]
      EmojiNode[22, 31] textOpen:[22, 23, ":"] text:[23, 30, "warning"] textClose:[30, 31, ":"]
        Text[23, 30] chars:[23, 30, "warning"]
````````````````````````````````


## Issue 168

# 168, Text with colons is incorrectly interpreted as an invalid emoji shortcut

```````````````````````````````` example 168, Text with colons is incorrectly interpreted as an invalid emoji shortcut: 1
На сервере выставлен пояс GMT 00:00. Оно всегда должно быть *"3:50 ночи"*, даже если
.
<p>На сервере выставлен пояс GMT 00:00. Оно всегда должно быть <em>&quot;3:50 ночи&quot;</em>, даже если</p>
.
Document[0, 84]
  Paragraph[0, 84]
    Text[0, 60] chars:[0, 60, "На се … быть "]
    Emphasis[60, 73] textOpen:[60, 61, "*"] text:[61, 72, "\"3:50 ночи\""] textClose:[72, 73, "*"]
      Text[61, 72] chars:[61, 72, "\"3:50 … ночи\""]
    Text[73, 84] chars:[73, 84, ", даж …  если"]
````````````````````````````````


