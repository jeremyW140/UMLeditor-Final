# UMLEditor
![Demo Screenshot](./screenshots/demo.png)
## 專案概述
本專案基於 Java 的 UML 編輯器，提供繪製 UML 類別圖和用例圖的基本功能。本編輯器包含中間畫布區域、左側功能區、及上方工具列，功能區支援在畫布中創建 UML 物件、UML 連接線、以及選取物件，而工具列提供更改物件名稱、群組/解散物件等操作。

## 功能需求

### 1. 基本功能按鈕
| 按鈕名稱 | 功能描述 |
|----------|----------|
| Select | 選取模式 |
| Association | 建立關聯關係 |
| Generalization | 建立泛化關係 |
| Composition | 建立組合關係 |
| Class | 建立類別物件 |
| Use Case | 建立用例物件 |

### 2. 主要功能
#### A. 建立物件
- 可在畫布中創建 Class 和 Use Case 基本物件
#### B. 移動物件
- 可在畫布中任意更改物件之位置
#### C. 連結物件
- 建立物件間關聯（Association）、泛化（Generalization）、組合（Composition）的關係線
#### D. 群組/取消群組物件
- 使用滑鼠框出欲群組之物件，以工具列 Edit -> Group 功能即可進行物件群組
- 先點擊已被群組之物件，在工具列使用 Edit -> Ungroup 解散群組 
#### E. 命名物件
- 物件處於選取狀態時，使用者可以在工具列點選 Edit -> ChangeName 來更改物件名稱選單
