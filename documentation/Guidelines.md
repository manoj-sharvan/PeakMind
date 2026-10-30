# PeakMind V2 — Wireframe-Based UI/UX Upgrade

You are a senior Android UI/UX designer and Kotlin Jetpack Compose engineer.

I have an existing native Android application named **PeakMind**, developed using Kotlin, Jetpack Compose and Material 3.

The application already has 17 implemented screens, navigation, exercise categories, workout UI, progress tracking and a successful build.

I have attached the original PeakMind Fitness App Wireframe Board. Treat this image as the PRIMARY visual design reference.

## Main objective

Upgrade the existing application to closely match the attached wireframe.

This is a refinement of the existing project, NOT a complete rebuild.

Preserve the existing working architecture, functionality, data models and navigation wherever possible.

## Phase 1 — Complete project audit

Before modifying code:

1. Inspect the complete Android project.
2. Identify all 17 existing screens and their Compose files.
3. Inspect the current theme, colours, typography, shapes and component hierarchy.
4. Inspect navigation and ViewModels.
5. Run the existing application in the emulator.
6. Capture screenshots of every existing screen.
7. Compare actual screens against the attached wireframe.
8. Create a screen-by-screen difference report.

Classify each issue as:
- Visual mismatch
- Missing component
- Incorrect spacing or proportions
- Incorrect typography
- Missing interaction
- Missing animation
- Missing exercise visual
- Navigation issue
- Accessibility issue

Do not claim that a screen matches the reference without visually checking it.

## Phase 2 — Upgrade the design system

Use the attached wireframe as the visual target.

Design direction:
- Premium fitness and wellness application.
- Dark navy / charcoal background.
- Electric lime primary action colour.
- Cyan / teal secondary accent.
- Rounded dark cards.
- Subtle gradients.
- Thin cyan borders where appropriate.
- Clear typography hierarchy.
- Consistent spacing and component sizes.
- Professional icons and illustrations.

Create reusable components for:
- Primary and secondary buttons.
- Workout cards.
- Exercise cards.
- Category cards.
- Progress indicators.
- Metric cards.
- Timer displays.
- Navigation bars.
- Level selection cards.
- Section headers.
- Search fields.
- Filter chips.

Support both dark and light themes.

Avoid excessive decoration and inconsistent card styles.

## Phase 3 — Upgrade all 17 screens

Use the reference board to refine the following screens:

1. Splash Screen
2. Onboarding
3. User Profile Setup
4. Home Dashboard
5. Workout Categories
6. Level Selection
7. Exercise Library
8. Active Workout
9. Rest Screen
10. Kegel Training
11. Pelvic-Floor Relaxation
12. Sexual Wellness
13. Meditation
14. Body Heat Management
15. Progress Tracking
16. Settings and Profile
17. Exercise Visual Guide

For each screen:
- Match the reference layout.
- Improve visual hierarchy.
- Fix spacing and alignment.
- Improve typography and readability.
- Use consistent card sizes and corner radii.
- Ensure buttons have real working actions.
- Handle loading, empty and error states.
- Make the screen responsive.

Do not remove existing functionality just to achieve visual similarity.

## Phase 4 — Exercise visual system

This is a major priority.

The wireframe contains four visual reference sections:

A. Main Exercise Image
B. Step-by-Step Movement
C. Muscle Anatomy
D. Pelvic Floor Education

Upgrade the Exercise Visual Guide to support all four.

For each exercise, provide:
- Correct starting position.
- Correct ending position.
- Movement sequence.
- Target muscles.
- Secondary muscles, if relevant.
- Equipment requirements.
- Breathing instructions.
- Common mistakes.
- Safety precautions.

Do not use the same generic image for every exercise.

Use accurate, individually selected exercise illustrations. Use original or appropriately licensed assets and keep them available offline.

Use medically reviewed or clearly labelled educational illustrations for pelvic-floor anatomy. Do not imply that illustrations are diagnostic.

## Phase 5 — Active workout experience

Refine the Active Workout screen to include:

- Large exercise demonstration.
- Exercise title.
- Target muscle labels.
- Difficulty level.
- Sets and reps.
- Rest duration.
- Clear instructions.
- Breathing guidance.
- Set completion indicator.
- Previous exercise.
- Pause and resume.
- Next exercise.
- Workout progress.

Improve the Rest screen with a smooth animated countdown and next-exercise preview.

Preserve existing working timer functionality.

## Phase 6 — Dashboard and progress

Home dashboard should clearly show:

- Greeting and profile avatar.
- Today's recommended workout.
- Start Workout button.
- Current and target weight.
- Six-month progress.
- Weekly workout count.
- Active minutes.
- Quick category shortcuts.

Progress screen should show actual saved data. Never display fabricated weight-loss progress or completed workouts as real user achievements.

## Phase 7 — Animations and interactions

Add subtle, purposeful animations:

- Screen transitions.
- Card selection.
- Button press feedback.
- Progress bar animation.
- Timer animation.
- Breathing pacer animation.
- Workout completion animation.
- Loading animation.

Ensure animations do not interfere with Android back navigation, touch interactions or accessibility.

## Phase 8 — Technical requirements

- Kotlin.
- Jetpack Compose.
- Material 3.
- Existing MVVM architecture.
- Existing Navigation Compose setup.
- Reusable composables.
- State-driven UI.
- Offline-compatible exercise assets.
- Proper lifecycle handling.
- Accessibility descriptions.
- No unnecessary dependencies.

Do not rewrite the entire application or introduce a new architecture without justification.

## Phase 9 — Validation

After the changes:

1. Build the complete project.
2. Run unit tests.
3. Run the app in the emulator.
4. Capture updated screenshots of all 17 screens.
5. Compare screenshots against the attached reference.
6. Test bottom navigation and back navigation.
7. Test all buttons and interactive controls.
8. Test workout timers and rest timers.
9. Check dark and light themes.
10. Fix regressions and build errors.

## Git and safety rules

- Inspect Git status before editing.
- Never overwrite unrelated user changes.
- Create a backup or dedicated feature branch.
- Keep changes focused and reversible.
- Do not delete working screens or features.
- Do not modify the application's package ID or signing configuration without approval.

## Implementation workflow

Work in small phases:

Phase A: Audit and visual comparison.

Phase B: Design system and reusable components.

Phase C: Dashboard, categories and level selection.

Phase D: Exercise library and visual guide.

Phase E: Active workout and rest experience.

Phase F: Wellness, pelvic-floor and meditation screens.

Phase G: Progress, settings and final polish.

After each phase:
- Build the application.
- Report changed files.
- Show screenshots.
- List remaining issues.
- Wait for my approval before starting the next phase.

**First task: Complete Phase A and present the visual difference report. Do not begin modifying the entire application immediately.**