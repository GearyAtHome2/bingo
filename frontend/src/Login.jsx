import { useState } from "react";
import api from "./api";

export default function Login({ onSuccess }) {
  const [email, setEmail] = useState("");
  const [mode, setMode] = useState("register");
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  async function submit(e) {
    e.preventDefault();
    setError(null);
    setLoading(true);

    try {
      const endpoint = mode === "register" ? "/register" : "/login";

      await api.post(endpoint, {
        email,
        password: "placeholder"
      });

      localStorage.setItem("email", email);
      onSuccess();
    } catch (err) {
      if (err.response?.data) {
        setError(err.response.data.error || err.response.data);
      } else {
        setError(err.message);
      }
    } finally {
      setLoading(false);
    }
  }

  return (
    <div style={{ padding: 20, maxWidth: 400, margin: "auto" }}>
      {/* Inject spinner keyframes */}
      <style>
        {`
          @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
          }
        `}
      </style>

      <h2>{mode === "register" ? "Register" : "Login"} to Bingo</h2>

      {error && (
        <div style={{ color: "red", marginBottom: 10 }}>
          {error}
        </div>
      )}

      <form onSubmit={submit}>
        <input
          placeholder="Username (this can be any old rubbish)"
          value={email}
          onChange={e => setEmail(e.target.value)}
          style={{ padding: 8, width: "100%", marginBottom: 10 }}
        />
        <button type="submit" style={{ padding: 10, width: "100%", marginBottom: 10 }}>
          {mode === "register" ? "Register" : "Login"}
        </button>
      </form>

      <button
        type="button"
        onClick={() => setMode(mode === "register" ? "login" : "register")}
        style={{ padding: 6, width: "100%", marginBottom: 20 }}
      >
        {mode === "register"
          ? "Already have an account? Login"
          : "New user? Register"}
      </button>

      {/* Loading spinner and text */}
      {loading && (
        <div style={{
          marginTop: 40,
          display: "flex",
          flexDirection: "column",
          alignItems: "center"
        }}>
          <div style={{
            border: "4px solid #f3f3f3",
            borderTop: "4px solid #555",
            borderRadius: "50%",
            width: 40,
            height: 40,
            animation: "spin 1s linear infinite",
            marginBottom: 10
          }}></div>
          <div>Sorry, I used a cheap server based in Frankfurt so it might take a while to log in...</div>
        </div>
      )}
    </div>
  );
}
