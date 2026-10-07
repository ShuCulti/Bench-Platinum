import "./NavBar.css"

export function Navbar(){
    return(
        <>
            <nav className="navbar">
                <h3 className= "" style={{position: "relative", right: "17.5%"}}>LOCAL/BENCH</h3>
                <div className= "homepage-navbar-btn" > Leaderboard </div>
                <div className= "homepage-navbar-btn"> Models </div>
                <div className= "homepage-navbar-btn">  Methodology </div>
                <button style={{position: "relative", left: "17.5%"}}>SUBMIT DATA</button>

            </nav>
        </>
    )
}