import "./Footer.css"
import { useNavigate } from "react-router"
import { Zap } from "lucide-react"

const footerLinks = [
    {id: 1, name: "GitHub", link: "https://github.com/ShuCulti/Bench-Platinum", external: true},
    {id: 2, name: "Methodology", link: "/methodology"},
    {id: 3, name: "Download CLI", link: "/download"},
    {id: 4, name: "Data & privacy", link: "/methodology"},
]

export function Footer(){
    const navigate = useNavigate()

    function handleClick(footerLink){
        if (footerLink.external){
            window.open(footerLink.link, "_blank")
        } else {
            navigate(footerLink.link)
        }
    }

    return(
        <>
            <footer className="footer">
                <div className="footer-brand">
                    <div className="footer-logo">
                        <Zap size={18} color="#00ff9d"/>
                        <span>PLATINUM//BENCH</span>
                    </div>
                    <p className="footer-tagline">Open-source benchmarks for local LLM inference.</p>
                </div>
                <div className="footer-links">
                    {footerLinks.map((footerLink) => (
                        <span key={footerLink.id} className="footer-link" onClick={() => handleClick(footerLink)}>
                            {footerLink.name}
                        </span>
                    ))}
                </div>
            </footer>
        </>
    )
}
