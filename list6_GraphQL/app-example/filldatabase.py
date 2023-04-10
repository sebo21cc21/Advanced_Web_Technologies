import mysql.connector
import requests

db = mysql.connector.connect(
    host="localhost",
    user="admin",
    password="password",
    database="graph"
)


users_data = requests.get("https://jsonplaceholder.typicode.com/users").json()
for user in users_data:
    cursor = db.cursor()
    sql = "INSERT INTO users (id, name, email, login) VALUES (%s, %s, %s, %s)"
    val = (user["id"], user["name"], user["email"], user["username"])
    cursor.execute(sql, val)
    db.commit()


todos_data = requests.get("https://jsonplaceholder.typicode.com/todos").json()
for todo in todos_data:
    cursor = db.cursor()
    sql = "INSERT INTO todos (id, title, completed, user_id) VALUES (%s, %s, %s, %s)"
    val = (todo["id"], todo["title"], todo["completed"], todo["userId"])
    cursor.execute(sql, val)
    db.commit()

db.close()
