import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {BrowserRouter, Routes, Route} from "react-router";
import './index.css'
import HomePage from './pages/HomePage.jsx'
import LeaderboardPage from './pages/LeaderboardPage.jsx'
import ModelsPage from './pages/ModelsPage.jsx'
import NotFoundPage from './pages/NotFoundPage.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<HomePage/>}/>
              <Route path="/leaderboard" element={<LeaderboardPage/>}/>
              <Route path="/models" element={<ModelsPage/>}/>
              <Route path="*" element={<NotFoundPage/>}/>
          </Routes>
      </BrowserRouter>
  </StrictMode>,
)
