# Scam Shield

**Recognise, report and recover from online scams.**

Scam Shield is a Java Swing desktop application developed for the
Object Oriented Programming module. It supports **UN Sustainable
Development Goal 16 – Peace, Justice and Strong Institutions** by helping
people share scam reports, learn the warning signs, and get guidance
after being scammed.

## Sections

| Section | Package | Developer |
|---|---|---|
| Scam Reports | `ca.scamshield.reports` | Inès Khabkhabi |
| Awareness Quiz | `ca.scamshield.quiz` | Michal Szaroma |
| Victim Support | `ca.scamshield.support` | Catherine Njaro |

## Features (planned)

- **Scam Reports** – submit a report of a phishing email, phone scam or
  fake website, view all reports with a risk level, and search or
  delete reports.
- **Awareness Quiz** – take a quiz on spotting scams, manage the question
  bank (add, search, delete) and view past scores and the best score.
- **Victim Support** – open a support case, view recovery advice for
  each type of case, and find, resolve or remove cases.

All data will be saved to and loaded from files.

## How to run

1. Open the project in **Apache NetBeans** (Maven project).
2. Press **Run** (the green Play button). The app starts from
   `ca.scamshield.ScamShield`, which opens the main menu.

## Project structure

- `ca.scamshield` – main app class and main menu
- `ca.scamshield.reports` – Scam Reports section
- `ca.scamshield.quiz` – Awareness Quiz section
- `ca.scamshield.support` – Victim Support section

## Progress

- [x] Checkpoint 1 – Project proposal and class diagram
- [x] Checkpoint 2 – User interface and navigation
- [ ] Checkpoint 3 – Individual sections (29 November)
- [ ] Final submission – Full prototype with file storage (4 December)
