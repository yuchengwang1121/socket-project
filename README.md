## socket-project
### Outline
  * The language I used and the related package
  * The method to build socket system
  * The registration system
  * The game of guessing number competition
  
### Language & Package
  ![image](https://user-images.githubusercontent.com/73687292/204484182-9e6004bb-6a45-4a57-8ee8-ade49d47343e.png)
  * **Server :**
    ```java=
    import java.net.*
    import java.io.*
    import java.util.HashMap
    import java.util.Map
    ```
  * **Client :**
    ```java=
    import java.net.*
    import java.io.*
    import java.awt.*
    import java.awt.event.*
    ```
  * **Rules :**
    ```java=
    import java.awt.*
    import java.awt.event.*
    import java.io.IOException
    import java.util.*
    ```
### Socket System
  * Use $MultiThreading$ to implement. Whenever a `Client` want to connect to `Server`, create new Socket for the connection.
### Registration system
  * Pass the client account password to the SERVER and judge. And there are five situations about the registration system.
    1. When you log in without registration, it will display $Please\ register\ first$.
    2. When the registration is successful, it will show that the registration is successful, and save the account password in a .txt file.
    3. When you have already registered but press `Register`, it will show that $you\ have\ already\ registered$.
    4. When you enter a wrong registration password, it will show that the password is $wrong$.
    5. Successfully log in and enter the game screen
  * The screenshot of situation i. ~ iv.
    ![image](https://user-images.githubusercontent.com/73687292/204487596-840c6cbe-5651-4dba-98a9-1afe31f5b4c0.png)

### Final Result
  * The game is to guess the number and play against the opponent. The rule is to guess a number between 1 and 100. When you press enter, it will be judged.
      * If the number is bigger then the answer, it will displayed $Lower$ to notice player to guess downwards.
      * If the number is smaller then the answer, it will displayed $Higher$ to notice player to guess upwards.
  * Meanwhile, the score will be reduced after each guessing until the input is correct or the score is below 0. 
  * If you make a mistake, just press the `Clean` button. 
  * When one is finished while others is not, the server will tell the client who finished first to wait
  * After both finished, server will judge who's score is higher. If tie, than compare who use less times.
  * After all, it will send $You Win~！！$ back to the winner's screen, while the other with $You Lose QQ$ .
  
finished when 2020/12/15
Modified when 2022/11/29
