# OutSideCommander
外部からJSONをPOSTしてコマンドを実行するプロジェクト

## 簡単な説明
SpringBootでPostされたコマンドが記述されたJSONを読み込み、実行し、値を返す、
RestAPIを作成しました。

## 必要要件

- OpenJDK 17
- Gradle 5.7.1 (Build時に使用します)
- ※Jarを動かすのみ場合はOpenJDK 17のみで結構です。

## 使い方

1. [Server側] ReleaseからJarファイルをダウンロードして、サーバーに設置してください。
2. [Server側] `java -jar OutsideCommander-1.0.0.jar` を実行してjarを起動してください。
3. [Client側] Releaseからtest.httpファイルをダウンロードしてください。
4. [Client側] Rest-Clientプラグインが入っているVSCodeで、実行できます。
5. [Client側] JSONの"command"変数の中身をを変更することでコマンドを変更できます(例`ls -la`)

## Jarビルド方法

```
$ git clone https://github.com/HaneServer/OutsideCommander/
$ cd OutsideCommander
$ ./gradlew build
```

## 開発者

[@massa_san_](https://twitter.com/massa_san_)

## ライセンス

MIT LICENSE