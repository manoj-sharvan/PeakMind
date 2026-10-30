# PEAKMIND V2 — COMPLETE UI/UX, FUNCTIONALITY AND EXERCISE ANIMATION SPECIFICATION

## 1. PROJECT OBJECTIVE

Upgrade the existing PeakMind Android application into a complete, polished, interactive six-month fitness and wellness application.

Existing technology:
- Kotlin
- Jetpack Compose
- Material 3
- MVVM
- Navigation Compose
- Existing ExerciseRepository and UserProfileManager

The existing application already contains 17 screens. Preserve and improve the existing implementation.

Do not rebuild the project from scratch.

The attached PeakMind wireframe board is the primary visual reference.

Every screen must have:
1. Complete wireframe layout.
2. Component-by-component specifications.
3. Working interactions.
4. Navigation behavior.
5. Loading, empty and error states.
6. Data persistence where appropriate.
7. Accessibility support.
8. Responsive layout.
9. Consistent dark and light themes.

---

# 2. GLOBAL DESIGN SYSTEM

## 2.1 Visual identity

Application name: PeakMind.

Design direction: Premium fitness, wellness and personal transformation.

Colours:
- Background: #0F1115
- Secondary background: #131922
- Card surface: #181B22
- Elevated surface: #202832
- Primary: #CCFF00
- Secondary: #06B6D4
- Primary text: #FFFFFF
- Secondary text: #A7B0BD
- Success: #36D399
- Warning: #FBBF24
- Error: #F87171

Use electric lime for important actions and progress. Use cyan/teal for secondary information and selected visual accents.

## 2.2 Typography

Create reusable typography styles:
- Display: splash branding.
- Headline: screen titles.
- Title: section headings.
- Body: descriptions.
- Label: buttons and chips.
- Caption: metadata and hints.
- Numeric: weight, timers, sets and progress.

Maintain readable font sizes and accessible contrast.

## 2.3 Spacing and shapes

Centralize:
- 4dp spacing system.
- 18–20dp standard card radius.
- 12–16dp compact card radius.
- Consistent button heights.
- Standard icon dimensions.
- Consistent screen horizontal padding.

## 2.4 Global components

Create reusable components:

1. PeakMindTopBar
2. PeakMindBottomNavigation
3. PeakMindPrimaryButton
4. PeakMindSecondaryButton
5. PeakMindIconButton
6. PeakMindWorkoutCard
7. PeakMindExerciseCard
8. PeakMindCategoryCard
9. PeakMindMetricCard
10. PeakMindProgressCard
11. PeakMindLevelCard
12. PeakMindSearchBar
13. PeakMindFilterChip
14. PeakMindDropdown
15. PeakMindTextField
16. PeakMindProfileAvatar
17. PeakMindTimer
18. PeakMindCircularProgress
19. PeakMindLinearProgress
20. PeakMindConfirmationDialog
21. PeakMindBottomSheet
22. PeakMindEmptyState
23. PeakMindErrorState
24. PeakMindLoadingState
25. PeakMindExerciseGifPlayer

All components must use centralized theme tokens.

---

# 3. COMPLETE PROFILE MANAGEMENT

Profile editing is a core requirement.

Users must be able to edit their information at any time, not only during onboarding.

## 3.1 Profile fields

Create a complete editable profile containing:

### Personal information
- Profile photo.
- Display name.
- Age.
- Date of birth (optional).
- Height.
- Current weight.
- Target weight.
- Preferred measurement units.

### Fitness information
- Fitness level.
- Main fitness goal.
- Secondary fitness goals.
- Workout duration preference.
- Preferred workout days.
- Preferred workout time.
- Available equipment.
- Exercise experience.

### Wellness preferences
- Meditation preference.
- Breathing session preference.
- Pelvic-floor training preference.
- Sexual wellness education preference.
- Hydration reminders.
- Recovery reminders.

Allow users to skip optional information.

Do not collect unnecessary sensitive personal information.

## 3.2 Edit Profile screen

Layout:

Top:
- Back button.
- Title: Edit Profile.
- Save button.

Sections:
1. Profile photo and display name.
2. Personal information.
3. Body measurements.
4. Fitness goals.
5. Fitness level.
6. Workout preferences.
7. Equipment.
8. Wellness preferences.

Each field must be editable using an appropriate control:
- Text input.
- Number input.
- Date picker.
- Dropdown.
- Single-select chips.
- Multi-select chips.
- Slider where appropriate.
- Image picker for profile photo.

Actions:
- Save changes.
- Cancel changes.
- Reset unsaved changes.
- Field validation.
- Confirmation before discarding unsaved changes.

When saved:
- Update the dashboard.
- Update profile summary.
- Recalculate BMI when height or weight changes.
- Update target progress.
- Ask for confirmation before significantly changing an existing workout programme.
- Preserve historical measurements and completed workouts.

## 3.3 Profile data persistence

Persist the profile using the existing architecture.

Use Room for structured profile and measurement history where appropriate, and DataStore for lightweight preferences.

All saved fields must survive:
- App restart.
- Device restart.
- Screen navigation.
- Theme changes.

Never reset user data when editing unrelated profile fields.

---

# 4. SCREEN-BY-SCREEN COMPLETE SPECIFICATION

## SCREEN 1 — SPLASH SCREEN

Components:
- PeakMind logo.
- Mountain emblem.
- App name.
- Brand tagline.
- Background gradient.
- Loading indicator.

Functionality:
- Initialize saved preferences.
- Load user profile.
- Load workout state.
- Check onboarding completion.
- Navigate to onboarding for a new user.
- Navigate to dashboard for an existing user.

States:
- Loading.
- Initialization error.
- First launch.
- Returning user.

Animation:
- Logo fade and scale.
- Subtle mountain background movement.
- Loading indicator.

Do not display a fake loading process for an unnecessarily long duration.

## SCREEN 2 — ONBOARDING

Header:
- Back or skip control where appropriate.
- Onboarding page indicator.

Four feature cards:
1. Workouts.
2. Pelvic Health.
3. Wellness.
4. Progress.

Each card:
- Icon or illustration.
- Heading.
- Short explanation.
- Tap interaction.

Bottom:
- Page indicator.
- Next.
- Get Started.

Functionality:
- Next and previous onboarding pages.
- Skip onboarding.
- Persist onboarding completion.
- Navigate to profile setup.

## SCREEN 3 — USER PROFILE SETUP

Show editable setup cards for:
- Name.
- Age.
- Height.
- Current weight.
- Target weight.
- Fitness level.
- Workout duration.
- Equipment.

Each field opens an appropriate input or selection dialog.

Fitness level:
- Beginner.
- Intermediate.
- Advanced.
- Master.

Workout duration:
- 15 minutes.
- 30 minutes.
- 45 minutes.
- 60 minutes.

Equipment:
- Bodyweight.
- Exercise mat.
- Stable chair.
- Resistance band.
- Dumbbells.
- Other equipment.

Buttons:
- Back.
- Next.
- Save profile.

Validation:
- Positive and reasonable numeric values.
- Target weight must be positive.
- Required fields must be completed.
- Explain invalid values clearly.

Do not automatically assign an advanced exercise programme to a beginner.

## SCREEN 4 — HOME DASHBOARD

Header:
- Greeting using saved display name.
- Profile avatar.
- Notification icon.
- Settings shortcut.

Main hero card:
- Today's workout.
- Workout category.
- Difficulty level.
- Estimated duration.
- Exercise count.
- Start Workout button.

Weight section:
- Current weight.
- Target weight.
- Latest measurement date.
- Log Weight action.

Six-month programme:
- Current programme week/month.
- Completion percentage.
- Progress indicator.
- View Programme action.

Weekly statistics:
- Workouts completed.
- Weekly workout target.
- Active minutes.
- Meditation activity.

Quick-access categories:
- Full Body.
- Weight Loss.
- Cardio.
- Warm-up.
- Cool-down.
- Kegel Training.
- Pelvic Floor.
- Sexual Wellness.
- Meditation.
- Recovery.

Functionality:
- All metrics must use saved data.
- Dashboard refreshes after profile or workout changes.
- Start Workout opens the correct session.
- Profile avatar opens profile.
- Notification icon opens notification centre.
- Empty state for users who have not started a workout.

Never show fabricated progress as actual user progress.

## SCREEN 5 — WORKOUT CATEGORIES

Display a responsive two-column grid.

Categories:
1. Full Body.
2. Weight Loss.
3. Cardio.
4. Warm-up.
5. Cool-down.
6. Kegel Training.
7. Pelvic-Floor Relaxation.
8. Sexual Wellness.
9. Meditation.
10. Body Heat Management.

Each category card:
- Image or illustration.
- Category title.
- Description.
- Exercise/session count.
- Difficulty information.
- Favourite action where applicable.

On tap:
- Open category details.
- Show relevant exercises or sessions.
- Show duration and difficulty filters.
- Allow the user to select a session.

## SCREEN 6 — LEVEL SELECTION

Four levels:

Beginner:
- Introductory movements.
- Low exercise volume.
- More rest.
- Focus on technique.

Intermediate:
- Moderate volume.
- Improved endurance.
- More movement variations.

Advanced:
- Higher exercise volume.
- More challenging variations.
- Appropriate recovery.

Master:
- Advanced combinations.
- Higher training complexity.
- Only available when suitable for the user's ability.

Each card:
- Level name.
- Description.
- Suggested duration.
- Training volume.
- Exercise count.
- Selection indicator.

Actions:
- Select level.
- Continue.
- Change level later through profile or programme settings.

Level changes must not delete workout history.

## SCREEN 7 — EXERCISE LIBRARY

Top:
- Screen title.
- Search field.
- Filter controls.

Filters:
- All.
- Full Body.
- Cardio.
- Warm-up.
- Cool-down.
- Kegel.
- Pelvic Floor.
- Mobility.

Additional filters:
- Difficulty.
- Duration.
- Equipment.
- Target muscle.

Exercise card:
- Exercise GIF thumbnail or animated preview.
- Exercise name.
- Category.
- Difficulty.
- Target muscles.
- Equipment.
- Recommended sets.
- Recommended reps or duration.
- Rest duration.
- Favourite icon.

Actions:
- Tap exercise to open details.
- Tap favourite to save.
- Search by name.
- Clear filters.
- Sort by name, duration or difficulty.

Add:
- Favourite exercises screen.
- Recently viewed exercises.
- Empty search state.

## SCREEN 8 — ACTIVE WORKOUT

Top bar:
- Back.
- Workout title.
- Favourite.
- More options.

Workout progress:
- Current exercise number.
- Total exercises.
- Progress bar.

Main demonstration:
- Large animated exercise GIF.
- Exercise name.
- Target muscles.
- Difficulty.

Exercise metrics:
- Sets.
- Reps.
- Duration.
- Rest time.

Instructions:
- Starting position.
- Step-by-step movement.
- Breathing instructions.
- Common mistakes.
- Safety notes.

Controls:
- Previous exercise.
- Start/Pause.
- Complete set.
- Next exercise.
- Skip exercise.
- End workout.

Timer behavior:
- Start only when requested.
- Pause when the user pauses.
- Handle app backgrounding correctly.
- Restore the remaining timer after returning to the app.
- Do not silently mark exercises complete.

At workout completion:
- Summary screen.
- Completed exercises.
- Total duration.
- Sets completed.
- Optional user notes.
- Save workout.

## SCREEN 9 — REST SCREEN

Components:
- Circular countdown.
- Next exercise GIF preview.
- Next exercise title.
- Next exercise sets and reps.
- Skip Rest.
- Resume.
- Add 15 seconds.

Functionality:
- Accurate countdown.
- Pause and resume.
- Extend rest.
- Skip rest.
- Transition to next exercise.
- Restore state after temporary app backgrounding.

## SCREEN 10 — KEGEL TRAINING

Show routines:
- Basic Slow Kegel.
- Quick Kegel.
- Seated Kegel.
- Standing Kegel.
- Supine Kegel.
- Breathing Coordination.

Each routine:
- Description.
- Position.
- Guided timer.
- Contraction duration.
- Relaxation duration.
- Breathing guidance.
- Completion summary.

Interactive pacer:
- Contract phase.
- Relax phase.
- Breathing synchronization.
- Clear visual timer.
- Stop/pause control.

Important:
- Teach complete relaxation between contractions.
- Do not encourage forceful or continuous contractions.
- Include guidance to stop if pain occurs.
- Provide pelvic-floor relaxation alternatives.
- Do not present Kegels as a guaranteed treatment for ED or premature ejaculation.

## SCREEN 11 — PELVIC-FLOOR RELAXATION

Routines:
1. Diaphragmatic Breathing.
2. Pelvic-Floor Awareness.
3. Pelvic Tilts.
4. Cat-Cow.
5. Child's Pose.
6. Hip Mobility.

Each routine:
- Individual exercise GIF.
- Starting position.
- Movement instructions.
- Duration.
- Breathing.
- Relaxation focus.
- Safety guidance.

Guided session:
- 5-minute default session.
- Countdown.
- Pause/resume.
- Next movement.
- Session completion.

## SCREEN 12 — SEXUAL WELLNESS

Educational categories:
1. Arousal Awareness.
2. Start-Stop Technique.
3. Relaxation Breathing.
4. Partner Communication.
5. ED Wellness.
6. Progress Notes.

Each category opens a detailed educational screen.

Arousal Awareness:
- Explain arousal awareness.
- Use a simple subjective awareness scale.
- Explain early signs of increasing arousal.
- Teach slow breathing and relaxation.

Start-Stop:
- Explain the behavioural technique.
- Include user-controlled session notes.
- Encourage communication and consent.
- Do not promise a guaranteed result.

Relaxation Breathing:
- Guided breathing timer.
- Gentle animation.
- Pause and stop controls.

Partner Communication:
- Respectful communication guidance.
- Consent and comfort.

ED Wellness:
- General lifestyle education.
- Encourage professional evaluation for persistent symptoms.
- No medication recommendations or unsupported medical claims.

Progress Notes:
- Private notes.
- Date and time.
- Edit note.
- Delete note.
- Confirm deletion.
- Keep notes private and locally protected where possible.

## SCREEN 13 — MEDITATION

Sessions:
- Beginner Meditation: 10 minutes.
- Intermediate Meditation: 15 minutes.
- Advanced Meditation: 20 minutes.
- Master Meditation: 30 minutes.
- Breathing Timer: 3–10 minutes.
- Guided Body Scan: 15 minutes.

Components:
- Session illustration.
- Duration.
- Description.
- Start button.
- Breathing animation.
- Timer.
- Pause/resume.
- Completion summary.

Optional soundscape:
- Ambient sound toggle.
- Volume control.
- Stop sound.

Do not require internet access for core breathing and meditation timers.

## SCREEN 14 — BODY HEAT MANAGEMENT

Sections:
1. Cool-down routines.
2. Hydration reminders.
3. Slow breathing.
4. Recovery exercises.
5. Hot weather safety.
6. Warning signs.

Hydration:
- User-configurable daily target.
- Adjustable glass size.
- Add water.
- Undo last entry.
- Daily total.
- Reset at local day boundary.

Recovery:
- Cool-down exercise GIFs.
- Gentle movement.
- Breathing.
- Rest guidance.

Hot weather:
- Hydration reminders.
- Rest guidance.
- Avoiding excessive heat exposure.

Warning signs:
- Clearly distinguish ordinary discomfort from urgent symptoms.
- Explain when to stop exercise and seek medical attention.
- Do not claim that drinking water or cooling exercises treat heat illness.

## SCREEN 15 — PROGRESS TRACKING

Tabs:
- Weight.
- Waist.
- Activity.

Weight:
- Current weight.
- Target weight.
- Historical measurements.
- Trend chart.
- Add measurement.
- Edit measurement.
- Delete measurement with confirmation.

Waist:
- Waist measurement.
- Historical records.
- Trend chart.

Activity:
- Workout count.
- Workout duration.
- Weekly activity chart.
- Exercise completion.
- Meditation streak.

Charts:
- Interactive data points.
- Tooltips.
- Date labels.
- No fabricated values.

Add Measurement:
- Date.
- Weight or waist value.
- Optional notes.
- Save.
- Edit.
- Delete.

## SCREEN 16 — SETTINGS AND PROFILE

Profile summary:
- Profile photo.
- Display name.
- Age.
- Height.
- Current weight.
- Target weight.

Settings:
1. Edit Profile.
2. Language.
3. Theme.
4. Notifications.
5. Workout Reminders.
6. Measurement Units.
7. Privacy.
8. Export Data.
9. Reset Programme.

Language:
- English initially.
- Design for future Tamil support.

Theme:
- Dark.
- Light.
- System default.

Units:
- Metric.
- Imperial.

Notifications:
- Workout reminders.
- Hydration reminders.
- Meditation reminders.
- Recovery reminders.

Reminders:
- Enable/disable.
- Choose days.
- Choose time.
- Save reminder.

Privacy:
- Explain stored information.
- Provide data deletion controls.
- Confirm destructive actions.

Export:
- Export workout history.
- Export measurements.
- Export profile data.

Reset:
- Reset programme only.
- Preserve profile and history unless explicitly requested.
- Confirmation dialog before destructive actions.

## SCREEN 17 — EXERCISE VISUAL GUIDE

Each exercise detail screen must contain:

A. Main animated demonstration.

B. Starting and ending positions.

C. Step-by-step movement.

D. Primary and secondary muscle information.

E. Equipment.

F. Breathing instructions.

G. Common mistakes.

H. Safety precautions.

I. Related exercises.

J. Add to favourites.

K. Start exercise.

For pelvic-floor education:
- Use clearly labelled educational anatomical diagrams.
- Identify relevant structures accurately.
- Do not present illustrations as medical diagnosis.

---

# 5. ANIMATED GIF SYSTEM — REQUIRED FOR EVERY EXERCISE

This is a major requirement.

Every exercise in the exercise database must have its own individual animated GIF demonstration.

Do not reuse the same GIF for unrelated exercises.

## 5.1 GIF visual requirements

Each GIF should demonstrate:
1. Starting position.
2. Movement.
3. Ending position.
4. Return to starting position when the exercise is cyclic.

The animation must:
- Show the correct body posture.
- Show the movement clearly.
- Use a consistent camera angle.
- Have a clean background.
- Avoid visual obstructions.
- Clearly identify the moving body parts.
- Loop smoothly where appropriate.

## 5.2 Exercise GIF coverage

Create a separate animation asset for every exercise in the final exercise catalogue, including:

### Warm-up
- Marching in Place.
- Arm Circles.
- Shoulder Rolls.
- Torso Rotation.
- Hip Circles.
- Leg Swings.
- Ankle Circles.

### Full body
- Bodyweight Squat.
- Wall Push-up.
- Knee Push-up.
- Standard Push-up.
- Glute Bridge.
- Reverse Lunge.
- Bird Dog.
- Dead Bug.
- Plank.
- Side Plank.

### Cardio
- Marching in Place.
- Step Jacks.
- Low-impact High Knees.
- Standing Knee Raises.
- Modified Mountain Climbers.
- Shadow Boxing.

### Mobility and recovery
- Cat-Cow.
- Child's Pose.
- Hip Flexor Stretch.
- Hamstring Stretch.
- Chest Stretch.
- Calf Stretch.
- Gentle Spinal Rotation.

### Pelvic-floor awareness
- Diaphragmatic Breathing.
- Pelvic-Floor Awareness.
- Pelvic Tilts.
- Supine Relaxation.
- Supported Child's Pose.

### Kegel education
- Basic Slow Kegel.
- Quick Kegel.
- Seated Kegel.
- Standing Kegel.
- Supine Kegel.
- Breathing Coordination.

For Kegel demonstrations, use clear educational diagrams or subtle visual animations rather than pretending that external body movement accurately shows internal pelvic-floor contraction.

This list is a starting catalogue. Make the data model extensible so additional exercises can be added without modifying the UI architecture.

## 5.3 GIF asset management

Suggested folder:

app/src/main/assets/exercises/

Organize by category:

exercises/
- warmup/
- full_body/
- cardio/
- mobility/
- pelvic_floor/
- kegel/
- recovery/

Example:

bodyweight_squat.gif
wall_pushup.gif
glute_bridge.gif
reverse_lunge.gif
cat_cow.gif
diaphragmatic_breathing.gif

Use a unique asset for each exercise.

Create an exercise asset manifest containing:
- Exercise ID.
- Exercise name.
- GIF asset path.
- Thumbnail path.
- Category.
- Difficulty.
- Target muscles.
- Equipment.
- Duration or reps.
- Instructions.
- Breathing.
- Safety notes.

## 5.4 GIF playback component

Create a reusable PeakMindExerciseGifPlayer.

Features:
- Animated playback.
- Play/pause.
- Replay.
- Optional slow-motion mode.
- Loop control.
- Full-screen viewing.
- Loading placeholder.
- Missing asset fallback.
- Offline playback.
- Accessibility description.

Performance:
- Avoid loading all exercise GIFs simultaneously.
- Load only visible or requested animations.
- Pause off-screen animations.
- Release resources when appropriate.
- Cache efficiently.
- Use appropriately compressed assets.
- Test memory usage on lower-end Android devices.

Check existing image-loading libraries before adding dependencies.

GIF is the requested asset format. If a particular animation is too large or inefficient, propose an animated WebP or MP4 alternative and explain the trade-off before changing its format.

## 5.5 GIF asset creation workflow

For every exercise:
1. Define the correct movement.
2. Prepare a movement storyboard.
3. Create or source an appropriate animation.
4. Verify the movement.
5. Check asset licensing and attribution.
6. Optimize the GIF.
7. Add it to the exercise manifest.
8. Test playback inside the app.
9. Verify offline playback.

Do not invent that GIF assets exist if they have not been created or supplied.

Do not use copyrighted commercial workout animations without permission.

If an asset is missing, show a clear placeholder and report it in the asset completion list.

---

# 6. USER INTERACTION RULES

Every interactive component must have a defined action.

For each control document:
- Component name.
- Initial state.
- User action.
- State change.
- Result.
- Navigation destination, if applicable.
- Persistence requirement.
- Error behavior.

Examples:

Profile Save:
Tap → validate fields → persist changes → refresh profile and dashboard → show confirmation.

Favourite:
Tap heart → update favourite state → persist → update favourites list.

Complete Set:
Tap → mark current set complete → update progress → start rest if configured.

Add Measurement:
Tap → open form → validate value → save → refresh chart.

Reset Programme:
Tap → show confirmation → reset programme state only → preserve profile and historical records.

No decorative or non-functional buttons.

---

# 7. NAVIGATION ARCHITECTURE

Primary bottom navigation:
- Home.
- Workouts.
- Exercises.
- Progress.
- Profile.

Nested screens:
- Exercise Details.
- Active Workout.
- Rest.
- Workout Summary.
- Edit Profile.
- Measurement Entry.
- Notification Centre.
- Settings.
- Guided Session.
- Sexual Wellness Details.

Requirements:
- Correct Android back behavior.
- Preserve scroll position when returning where appropriate.
- Avoid duplicate destinations.
- Preserve active workout state during temporary navigation.
- Deep links only where useful.
- Clear navigation after reset or onboarding.

---

# 8. DATA MODELS

Create or improve structured models for:

UserProfile:
- id
- displayName
- age
- height
- currentWeight
- targetWeight
- fitnessLevel
- goals
- equipment
- preferredDuration
- preferredDays
- preferredWorkoutTime
- profileImageUri

Exercise:
- id
- name
- description
- category
- difficulty
- targetMuscles
- equipment
- gifAssetPath
- thumbnailPath
- sets
- reps
- duration
- restDuration
- instructions
- breathing
- commonMistakes
- safetyNotes

Workout:
- id
- name
- category
- level
- exercises
- estimatedDuration

WorkoutSession:
- id
- workoutId
- startTime
- endTime
- completedExercises
- completedSets
- duration
- completionStatus

Measurement:
- id
- date
- weight
- waist
- notes

Favourite:
- exerciseId
- createdAt

Reminder:
- id
- type
- enabled
- days
- time

ProgressNote:
- id
- date
- content

Use appropriate relationships and database migrations.

Do not store large GIF binary data directly inside Room.

---

# 9. SIX-MONTH PROGRAMME ENGINE

Create a configurable six-month programme.

It must:
- Start at a suitable level.
- Consider user preferences.
- Support gradual progression.
- Include rest days.
- Adjust workout duration.
- Allow schedule editing.
- Allow level changes.
- Preserve completed sessions.
- Support missed workout days without punishment.
- Provide a clear weekly overview.

Do not automatically increase exercise intensity simply because a calendar week has passed.

Use user feedback and appropriate progression rules.

Do not promise specific weight-loss, ED or PE outcomes.

---

# 10. ACCESSIBILITY AND QUALITY

- Accessible content descriptions.
- Appropriate text contrast.
- Scalable text.
- Touch targets of at least 48dp where possible.
- Screen-reader support.
- Reduced-motion consideration.
- Clear error messages.
- Keyboard and D-pad navigation where supported.
- Responsive layout.
- Correct Android lifecycle handling.

---

# 11. TESTING REQUIREMENTS

Test every screen and every interaction.

Profile:
- Edit each field.
- Save.
- Cancel.
- Reopen app.
- Verify saved values.

Exercise:
- Search.
- Filter.
- Favourite.
- Open details.
- Play GIF.
- Pause GIF.
- Replay GIF.
- Offline playback.

Workout:
- Start.
- Pause.
- Resume.
- Complete set.
- Skip.
- Rest timer.
- Finish.
- Verify history.

Progress:
- Add measurement.
- Edit measurement.
- Delete measurement.
- Verify charts.

Settings:
- Change theme.
- Change units.
- Set reminders.
- Export data.
- Confirm reset behavior.

Run:
- Unit tests.
- UI tests where practical.
- Build verification.
- Emulator testing.
- Memory and animation performance checks.

---

# 12. IMPLEMENTATION PHASES

Do not implement everything in one attempt.

Phase A — Project audit and wireframe comparison.

Phase B — Design system and reusable components.

Phase C — Profile management and editable settings.

Phase D — Dashboard, categories and level selection.

Phase E — Exercise database and individual GIF asset library.

Phase F — Exercise details and animated visual guide.

Phase G — Active workout and rest functionality.

Phase H — Kegel, pelvic-floor, sexual wellness and meditation.

Phase I — Progress tracking and six-month programme engine.

Phase J — Testing, accessibility, performance and release APK.

After every phase:
1. List changed files.
2. Explain implemented functionality.
3. Show screenshots.
4. Show test results.
5. Report missing GIF assets.
6. Report known limitations.
7. Wait for my approval.

## FINAL INSTRUCTION

First inspect the existing PeakMind project and create a complete implementation plan based on this specification.

Do not start implementing all phases immediately.

Start with a detailed screen-by-screen component inventory, interaction matrix, profile field map, exercise catalogue and GIF asset checklist.

Then wait for my approval.

Preserve all existing working features and the original PeakMind wireframe design direction.