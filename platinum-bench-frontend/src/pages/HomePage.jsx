import { useState, useEffect } from "react"
import { useNavigate } from "react-router"
import { Activity, BarChart3, Database, Cpu, ArrowUpRight } from "lucide-react"
import { Navbar } from "../components/NavBar.jsx"
import { Footer } from "../components/Footer.jsx"
import "../components/Components.css"
import "./HomePage.css"
import api from "../services/api.js"

const topRuns = [
    {id: 1042, hardware: "NVIDIA RTX 5070", gen: "98.4", tpw: "0.54", power: "182 W"},
    {id: 1038, hardware: "Apple M3 Max (40c GPU)", gen: "71.2", tpw: "1.98", power: "36 W"},
    {id: 1031, hardware: "AMD RX 7800 XT", gen: "69.8", tpw: "0.37", power: "190 W"},
    {id: 1027, hardware: "Intel Arc B580", gen: "62.1", tpw: "0.46", power: "135 W"},
    {id: 1019, hardware: "NVIDIA RTX 3060 12GB", gen: "55.6", tpw: "0.37", power: "150 W"},
    {id: 1004, hardware: "AMD Ryzen 9 5950X (CPU)", gen: "11.3", tpw: "0.09", power: "128 W"},
]

const steps = [
    {id: "01", title: "Install the CLI", text: "One download for Linux, macOS or Windows. It finds your llama.cpp build and detects your CPU and GPUs."},
    {id: "02", title: "Run a config", text: "Pick a model file and a config. The CLI runs every repetition, samples power and VRAM, and keeps each sample."},
    {id: "03", title: "Upload the job", text: "Results go up as one batch with your token. You see exactly what gets sent before anything leaves your machine."},
]

export default function HomePage(){
    const navigate = useNavigate()
    const [models, setModels] = useState([])

    useEffect(()=> {
        api.get("/models")
        .then(res => setModels(res.data))
        .catch(err => console.error("Failed to fetch models", err))
    }, [])

    return(
        <>
            <div className="page">
                <Navbar/>
                <HomeHero navigate={navigate}/>
                <HomeSteps/>
                <HomeLeaderboard navigate={navigate}/>
                <HomeModels models={models} navigate={navigate}/>
                <Footer/>
            </div>
        </>
    )
}

function HomeHero({ navigate }){
    return(
        <>
            <section className="HomePage-hero">
                <div className="HomePage-hero-copy">
                    <div className="HomePage-badge">
                        <Activity size={12}/>
                        Latest result: 2 min ago
                    </div>
                    <h1 className="HomePage-title">
                        Bench your<br/>
                        <span className="HomePage-title-gradient">metal</span>
                    </h1>
                    <p className="HomePage-intro">
                        The open-source leaderboard for local LLM inference. Compare tokens per second and tokens
                        per watt across GPUs, CPUs and quants, measured with llama.cpp on real contributor hardware.
                    </p>
                    <div className="HomePage-hero-buttons">
                        <button className="btn-primary" onClick={() => navigate("/download")}>Download CLI</button>
                        <button className="btn-secondary" onClick={() => navigate("/leaderboard")}>View rankings →</button>
                    </div>
                </div>
                <HomeTerminal/>
            </section>
        </>
    )
}

function HomeTerminal(){
    const lines = [
        {label: "Engine: ", value: "llama.cpp [BUILD] (Vulkan)"},
        {label: "Model file: ", value: "Qwen2.5-7B-Instruct-Q4_K_M.gguf"},
        {label: "Hardware: ", value: "NVIDIA RTX 5070 (12 GB)", accent: true},
    ]

    return(
        <>
            <div className="HomePage-terminal">
                <div className="HomePage-terminal-bar"></div>
                <div className="HomePage-terminal-header">
                    <div className="HomePage-terminal-dots">
                        <span></span><span></span><span></span>
                    </div>
                    <span>platinum-bench run --config default</span>
                </div>

                <div className="HomePage-terminal-output">
                    {lines.map((line) => (
                        <p key={line.label} className="HomePage-terminal-line">
                            <span className="HomePage-terminal-prompt">❯ </span>
                            <span className="HomePage-terminal-label">{line.label}</span>
                            <span className={line.accent ? "HomePage-terminal-accent" : ""}>{line.value}</span>
                        </p>
                    ))}
                    <p className="HomePage-terminal-line HomePage-terminal-done">Repetition 3 of 3 done. Uploading job…</p>
                </div>

                <div className="HomePage-terminal-results">
                    <div>
                        <p className="HomePage-terminal-result-label">Generation (median)</p>
                        <p className="HomePage-terminal-result-big">98.4 <span>t/s</span></p>
                    </div>
                    <div>
                        <p className="HomePage-terminal-result-label">Efficiency</p>
                        <p className="HomePage-terminal-result">0.54 <span>t/W</span></p>
                    </div>
                    <div>
                        <p className="HomePage-terminal-result-label">Avg power</p>
                        <p className="HomePage-terminal-result">182 <span>W</span></p>
                    </div>
                </div>
            </div>
        </>
    )
}

function HomeSteps(){
    return(
        <>
            <section className="HomePage-section">
                <h2 className="HomePage-section-title">How it works</h2>
                <div className="HomePage-steps">
                    {steps.map((step) => (
                        <div key={step.id} className="card HomePage-step">
                            <span className="HomePage-step-number">{}</span>
                            <h3 className="HomePage-step-title">{step.title}</h3>
                            <p className="HomePage-step-text">{step.text}</p>
                        </div>
                    ))}
                </div>
            </section>
        </>
    )
}

function HomeLeaderboard({ navigate }){
    return(
        <>
            <section className="HomePage-section HomePage-leaderboard">
                <div className="HomePage-section-header">
                    <div>
                        <h2 className="HomePage-section-title HomePage-section-title-icon">
                            <BarChart3 size={32} color="#00ff9d"/>
                            Global leaderboard
                        </h2>
                        <p className="page-subtitle">
                            Community results, median of every repetition. Pick a model file to compare hardware on the same workload.
                        </p>
                    </div>
                    <div className="HomePage-leaderboard-filters">
                        <label className="select-label">Model
                            <select className="select">
                                <option>Qwen2.5-7B</option>
                                <option>Llama-3.1-8B</option>
                                <option>Gemma-3-12B</option>
                            </select>
                        </label>
                        <label className="select-label">Quant
                            <select className="select">
                                <option>Q4_K_M</option>
                                <option>Q8_0</option>
                            </select>
                        </label>
                    </div>
                </div>

                <div className="table-wrapper">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Rank</th>
                                <th>Hardware</th>
                                <th>Model file</th>
                                <th className="table-right">Gen t/s</th>
                                <th className="table-right">Tokens/W</th>
                                <th className="table-right">Avg power</th>
                            </tr>
                        </thead>
                        <tbody>
                            {topRuns.map((run, index) => (
                                <tr key={run.id} className="HomePage-leaderboard-row" onClick={() => navigate(`/runs/${run.id}`)}>
                                    <td>
                                        <span className={`HomePage-rank ${index === 0 ? "HomePage-rank-first" : ""}`}>{index + 1}</span>
                                    </td>
                                    <td>
                                        <span className="HomePage-hardware">
                                            <Cpu size={16} color="#a1a1aa"/>
                                            {run.hardware}
                                        </span>
                                    </td>
                                    <td className="HomePage-muted">Qwen2.5-7B · Q4_K_M</td>
                                    <td className="table-right HomePage-gen">{run.gen}</td>
                                    <td className="table-right HomePage-tpw">{run.tpw}</td>
                                    <td className="table-right HomePage-muted">{run.power}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>

                <span className="HomePage-more-link" onClick={() => navigate("/leaderboard")}>
                    Full leaderboard with all filters <ArrowUpRight size={16}/>
                </span>
            </section>
        </>
    )
}

function HomeModels({ models, navigate }){
    return(
        <>
            <section className="HomePage-section">
                <div className="HomePage-section-header">
                    <h2 className="HomePage-section-title HomePage-section-title-icon">
                        <Database size={28} color="#a78bfa"/>
                        Benchmarked models
                    </h2>
                    <span className="HomePage-more-link" onClick={() => navigate("/models")}>
                        View all models <ArrowUpRight size={16}/>
                    </span>
                </div>

                {models.length === 0 && <p className="HomePage-muted">No models yet.</p>}

                <div className="HomePage-models">
                    {models.slice(0, 3).map((model) => (
                        <div key={model.id} className="card HomePage-model-card" onClick={() => navigate(`/models/${model.id}`)}>
                            <div className="HomePage-model-top">
                                <div>
                                    <h3 className="HomePage-model-name">{model.name}</h3>
                                    <p className="HomePage-model-family">{model.family}</p>
                                </div>
                                <span className="HomePage-model-params">{model.parameterB}B</span>
                            </div>
                        </div>
                    ))}
                </div>
            </section>
        </>
    )
}
