import React from 'react'
import ReactDOM from 'react-dom/client'
import reportWebVitals from './reportWebVitals'
import 'antd/dist/antd.css'
import Register from './register'
import Login from './login'
import App from './App'
import Movies from './movies'
import AdminPanel from './AdminMoviePanel'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import AdminUserPanel from './AdminUserPanel'

const root = ReactDOM.createRoot(document.getElementById('root'))
root.render(
  //<React.StrictMode>
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<App />} />
      <Route path="login" element={<Login />} />
      <Route path="register" element={<Register />} />
      <Route path="movies" element={<Movies />} />
      <Route path="adminMovies" element={<AdminPanel />} />
      <Route path="adminUsers" element={<AdminUserPanel />} />
      <Route
        path="*"
        element={
          <main style={{ padding: '1rem' }}>
            <p>There's nothing here!</p>
          </main>
        }
      />
    </Routes>
  </BrowserRouter>,
  //</React.StrictMode>,
)
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals()
