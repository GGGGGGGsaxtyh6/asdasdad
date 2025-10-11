import express from "express"
import jsonata from "jsonata"

const app = express()
app.use(express.json())

app.post("/transform", async (req, res) => {
  const { expression, input } = req.body
  try {
    const result = await jsonata(expression).evaluate(input)
    res.json({ result })
  } catch (error) {
    res.status(400).json({ error: error.message })
  }
})

app.listen(3000, () => {
  console.log("Servidor vulnerable corriendo en puerto 3000")
})