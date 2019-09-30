---
header-id: form-field-types
---

# フォームフィールドタイプ

[TOC levels=1-4]

フィールドのないフォームは、フォームではありません。Liferay Formsは、フォーム作成のニーズに応えるために、便利で高度に設定可能なフィールドタイプを提供します。ここで説明するフォームフィールドの例を参照するには、ユーザーガイドの[Forms](/docs/7-1/user/-/knowledge_base/u/forms)セクションにアクセスしてください。

![Figure 1: There are many useful out-of-the-box form field types.](../../images/forms-field-types.png)

段落：これはフォーム上の静的テキストです。ユーザーは、フォームのテキストフィールドにデータを入力しません。フォーム作成者は、フォームに表示されるフォームユーザーに表示されるテキストを入力します。これは、長い説明に役立ちます。

![Figure 2: Use Paragraph fields to enter longer instructions on Form Pages.](../../images/forms-paragraph.png)

テキストフィールド：ユーザーはこれらのフィールドにテキストを入力します。たとえば、氏名フィールドはテキストフィールドです。デフォルトでは、テキストフィールドはすべての入力を1行のテキストに保持します。より長い応答に対応するには、この例のようにテキストフィールドを構成するときに複数行の設定を選択します。
テキストフィールドの検証オプションを使用して、ユーザーが入力できるテキスト（1〜10の数字、電子メールアドレスなど）に制限を設定できます。

![Figure 3: Text fields can be single line or multi-line.](../../images/forms-multiline.png)

リストから選択：ユーザーは、選択肢のリストから1つのオプション（または、許可するように構成されている場合は複数）を選択します。選択肢は手動で入力するか、データプロバイダによって自動的に入力されます。たとえば、Country of the Worldデータプロバイダが入力したリストフィールドから、Country of Residenceフィールドを選択できます。

![Figure 4: Use a select from list field to let Users choose predefined options.](../../images/forms-select-list.png)

単一選択：ユーザーは、ラジオボタンを使用して、フォームに表示されるオプションのリストから1つのオプションを選択します。

![Figure 5: Single selection fields allow only one selection.](../../images/forms-single-selection.png)

日付：ユーザーは日付ピッカーを使用して日付を選択します。たとえば、生年月日フィールドでは、日付フィールドタイプを使用します。

![Figure 6: Date fields show a date picker so Users enter a valid date.](../../images/forms-date.png)

複数選択：ユーザーはチェックボックスから1つ以上のオプションを選択します（または、構成されている場合は切り替えます）。

![Figure 7: A multiple selection field can use a toggle.](../../images/forms-switcher.png)

グリッド：ユーザーはラジオボタンを使用して、行と列に配置されたオプションから選択します。行ごとに1つの選択を行うことができます。これは、複数の質問に同じ回答メトリックが必要な場合に役立ちます。たとえば、製品調査フォームでは、ユーザーに製品の特性のリストをWonderful、Pretty Good、Not So Good、またはAwfulとして評価するように求める場合があります。

![Figure 8: Grid fields use the same options (columns) for multiple categories (rows).](../../images/forms-grid.png)

数値：ユーザーは、数値フィールドに数値データ（整数または小数）を入力します。数字以外の入力は受け付けられません。たとえば、整数を受け入れる数値フィールドを構成して、ユーザーにペットの数を入力させます。

![Figure 9: Numeric fields accept only numeric input.](../../images/forms-numeric.png)

アップロード：ユーザーは、ドキュメントとメディアライブラリからファイルを選択するか、ローカルファイルシステムからファイルをアップロードできます。

![Figure 10: Upload fields let Users attach files to the form.](../../images/forms-upload.png)
