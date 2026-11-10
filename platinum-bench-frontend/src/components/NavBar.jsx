import "./NavBar.css"
import { useNavigate, useLocation } from "react-router"
import { Zap } from "lucide-react"

const navLinks = [
    {id: 1, name: "Leaderboard", link: "/leaderboard"},
    {id: 2, name: "Models", link: "/models"},
    {id: 3, name: "Hardware", link: "/hardware"},
    {id: 4, name: "Methodology", link: "/methodology"},
]

export function Navbar(){
    const navigate = useNavigate()
    const location = useLocation()

    return(
        <>
            <nav className="navbar">
                <div className="navbar-logo" onClick={() => navigate("/")}>
                    <Zap size={20} color="#00ff9d"/>
                    <span>PLATINUM</span>
                    <span className="navbar-logo-slash">//</span>
                    <span>BENCH</span>
                </div>

                <div className="navbar-links">
                    {navLinks.map((navLink) => (
                        <div
                            key={navLink.id}
                            className={`navbar-btn ${location.pathname.startsWith(navLink.link) ? "navbar-btn-active" : ""}`}
                            onClick={() => navigate(navLink.link)}
                        >
                            {navLink.name}
                        </div>
                    ))}
                </div>

                <div className="navbar-right">
                    <button className="navbar-myruns" onClick={() => navigate("/my-runs")}>My runs</button>
                    <button className="btn-primary" onClick={() => navigate("/download")}>Submit data</button>
                </div>
            </nav>
        </>
    )
}
