import { useState, useEffect } from "react"
import { useNavigate } from "react-router"
import { Search } from "lucide-react"
import { Navbar } from "../components/NavBar.jsx"
import { Footer } from "../components/Footer.jsx"
import "../components/Components.css"
import "./ModelsPage.css"
import api from "../services/api.js"

export default function ModelsPage(){
    const navigate = useNavigate()
    const [models, setModels] = useState([])
    const [activeFamily, setActiveFamily] = useState("All")
    const [searchQuery, setSearchQuery] = useState("")

    useEffect(()=> {
        api.get("/models")
        .then(res => setModels(res.data))
        .catch(err => console.error("Failed to fetch models", err))
    }, [])

    const families = ["All", ...new Set(models.map((model) => model.family))]

    const filteredModels = models.filter((model) => {
        const matchesFamily = activeFamily === "All" || model.family === activeFamily
        const matchesSearch = model.name.toLowerCase().includes(searchQuery.toLowerCase())
        return matchesFamily && matchesSearch
    })

    return(
        <>
            <div className="page">
                <Navbar/>
                <main className="page-main">
                    <div>
                        <h1 className="page-title">Models</h1>
                        <p className="page-subtitle">
                            Every model with at least one benchmarked file. A model can have several files (quants),
                            and each file is recognised by its SHA-256 hash, so the same bytes always count as the same file.
                        </p>
                    </div>

                    <div className="Models-controls">
                        <label className="select-label Models-search-label">Search by name
                            <span className="Models-search">
                                <Search size={18} color="#a1a1aa"/>
                                <input
                                    type="search"
                                    className="Models-search-input"
                                    placeholder="e.g. Qwen2.5-7B"
                                    value={searchQuery.trimStart()}
                                    onChange={(e) => setSearchQuery(e.target.value)}
                                />
                            </span>
                        </label>

                        <div className="Models-families">
                            <p className="Models-families-title">Family</p>
                            <div className="Models-family-buttons">
                                {families.map((family) => (
                                    <button
                                        key={family}
                                        className={`Models-family-btn ${activeFamily === family ? "Models-family-btn-active" : ""}`}
                                        onClick={() => setActiveFamily(family)}
                                    >
                                        {family}
                                    </button>
                                ))}
                            </div>
                        </div>
                    </div>

                    <div className="table-wrapper">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th>Model</th>
                                    <th>Family</th>
                                    <th className="table-right">Parameters</th>
                                </tr>
                            </thead>
                            <tbody>
                                {filteredModels.length === 0 && (
                                    <tr>
                                        <td colSpan={3} className="Models-empty">No models found.</td>
                                    </tr>
                                )}
                                {filteredModels.map((model) => (
                                    <tr key={model.id}>
                                        <td className="Models-name" onClick={() => navigate(`/models/${model.id}`)}>{model.name}</td>
                                        <td className="Models-muted">{model.family}</td>
                                        <td className="table-right">{model.parameterB}B</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>

                    <div className="Models-note">
                        <p className="Models-note-text">
                            Missing a model? You don't add it here. When the CLI uploads a run with a file the database
                            doesn't know yet, the model and file are created from its metadata and hash.
                        </p>
                        <span className="Models-note-link" onClick={() => navigate("/methodology")}>How files are recognised →</span>
                    </div>
                </main>
                <Footer/>
            </div>
        </>
    )
}
