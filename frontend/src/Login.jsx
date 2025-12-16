import { useState } from "react";
import api from "./api";

export default function Login({ onSuccess }) {
  const [email, setEmail] = useState("");
  const [mode, setMode] = useState("register");
  const [error, setError] = useState(null);

  async function submit(e) {
    e.preventDefault();
    setError(null);

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
    }
  }

  return (
    <div style={{ padding: 20, maxWidth: 400, margin: "auto" }}>
      <h2>{mode === "register" ? "Register" : "Login"} to Bingo</h2>

      {error && (
        <div style={{ color: "red", marginBottom: 10 }}>
          {error}
        </div>
      )}

      <form onSubmit={submit}>
        <input
          placeholder="Email"
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
        style={{ padding: 6, width: "100%" }}
      >
        {mode === "register"
          ? "Already have an account? Login"
          : "New user? Register"}
      </button>
    </div>
  );
}
