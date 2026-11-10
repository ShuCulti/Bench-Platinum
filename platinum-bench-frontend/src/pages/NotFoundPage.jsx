import { useNavigate, useLocation } from "react-router"
import { Navbar } from "../components/NavBar.jsx"
import { Footer } from "../components/Footer.jsx"
import "../components/Components.css"
import "./NotFoundPage.css"

export default function NotFoundPage(){
    const navigate = useNavigate()
    const location = useLocation()

    return(
        <>
            <div className="page">
                <Navbar/>
                <main className="NotFound-main">
                    <div className="card NotFound-card">
                        <p className="NotFound-request"><span className="NotFound-prompt">❯</span> GET {location.pathname}</p>
                        <p className="NotFound-status">404 Not Found</p>
                        <h1 className="NotFound-title">Nothing benchmarked here</h1>
                        <p className="NotFound-text">
                            This run, model or device doesn't exist, or its contributor hid it. Links to hidden runs stop working on purpose.
                        </p>
                        <div className="NotFound-buttons">
                            <button className="btn-primary" onClick={() => navigate("/leaderboard")}>Back to the leaderboard</button>
                            <button className="btn-secondary" onClick={() => navigate("/models")}>Browse models</button>
                        </div>
                    </div>
                </main>
                <Footer/>
            </div>
        </>
    )
}
