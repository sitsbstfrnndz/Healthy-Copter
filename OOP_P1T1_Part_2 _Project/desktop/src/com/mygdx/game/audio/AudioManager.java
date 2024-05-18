package com.mygdx.game.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;
import java.util.HashMap;
import java.util.Map;

public class AudioManager implements Disposable {
    private Map<String, Sound> soundEffects; // "Dictionary" of soundeffects, String is key for id
    private Map<String, Music> musics;// "Dictionary" of music, String is key for id
    private float sfxVolume; // global volume level for sound effects
    private float musicVolume; // global volume level of music tracks

    private static AudioManager instance;

    // Constructor to initialize soundEffects and musics maps, sets default vol for sounds and music to max 1.0f
    public AudioManager() {
        soundEffects = new HashMap<>();
        musics = new HashMap<>();
        sfxVolume = 1.0f; // Default volume (max)
        musicVolume = 1.0f; // Default volume (max)
        loadMusic("audio/music/music_main.mp3", "mainMusic");
        loadMusic("audio/music/music_game.mp3", "gameMusic");

    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    /**
     * Loads a sound effect from the specified file path and associates it with the provided key.
     * If a sound effect with the same key already exists, it will be disposed and replaced.
     * If the sound effect cannot be loaded (e.g., file not found or format unsupported),
     * an error will be logged. This method can be used to preload sound effects to avoid
     * delays when playing them during the game.
     *
     * @param path The file path of the sound effect to be loaded.
     * @param key The key to associate with the loaded sound effect for retrieval.
     */
    public void loadSoundEffect(String path, String key) {
        if (soundEffects.containsKey(key)) {
            soundEffects.get(key).dispose(); // Dispose of the existing sound effect
        }
        try {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
            soundEffects.put(key, sound);
        } catch (Exception e) {
            Gdx.app.error("AudioManager", "Could not load sound effect: " + path, e);
            // Optionally load a default sound effect if the desired one fails to load
        }
    }


    /**
     * Plays the sound effect identified by the given key. If the sound effect is not found,
     * a warning is logged. The global volume setting for sound effects is applied.
     *
     * @param key The key identifying the sound effect to be played.
     */
    public void playSoundEffect(String key) {
        Sound sound = soundEffects.get(key);
        if (sound != null) {
            sound.play(sfxVolume);
        } else {
            Gdx.app.error("AudioManager", "Attempted to play non-existent sound effect with key: " + key);
        }
    }


    /**
     * Loads a music track from the specified file path and associates it with the provided key.
     * If a music track with the same key already exists, it will be stopped, disposed, and replaced.
     * If the music cannot be loaded (e.g., file not found or format unsupported),
     * an error will be logged. This method is useful for managing background music and
     * other long-playing audio tracks. Note that unlike sound effects, music tracks
     * are streamed from the file and not loaded entirely into memory.
     *
     * @param path The file path of the music track to be loaded.
     * @param key The key to associate with the loaded music track for retrieval.
     */
    public void loadMusic(String path, String key) {
        if (musics.containsKey(key)) {
            Music existingMusic = musics.get(key);
            if (existingMusic.isPlaying()) {
                existingMusic.stop(); // Stop the music if it is currently playing
            }
            existingMusic.dispose(); // Dispose of the existing music track
        }
        try {
            Music music = Gdx.audio.newMusic(Gdx.files.internal(path));
            musics.put(key, music);
        } catch (Exception e) {
            Gdx.app.error("AudioManager", "Could not load music track: " + path, e);
            // Optionally load a default music track if the desired one fails to load
        }
    }

    /**
     * Plays the music associated with the given key. If the music is already playing, it will continue playing.
     * If the music is not found, a warning is logged. The music will loop if specified.
     *
     * @param key The key identifying the music to be played.
     * @param looping Whether the music should loop.
     */
    public void playMusic(String key, boolean looping) {
        Music music = musics.get(key);
        if (music != null) {
            if (!music.isPlaying()) { // Only play if not already playing
                music.setLooping(looping);
                music.setVolume(musicVolume);
                music.play();
            }
        } else {
            Gdx.app.error("AudioManager", "Attempted to play non-existent music with key: " + key);
        }
    }


    public void stopMusic(String key) { // stops playback of music identified by key
        Music music = musics.get(key);
        if (music != null) {
            music.stop();
        }
    }

    /**
     * Sets the global volume for all sound effects, ensuring the volume is within the range 0.0 to 1.0.
     * @param volume New volume value, which will be clamped between 0.0 and 1.0.
     */
    public void setSfxVolume(float volume) {
        sfxVolume = Math.max(0.0f, Math.min(volume, 1.0f)); // Clamp volume between 0 and 1
    }

    /**
     * Sets the global volume for all music tracks, ensuring the volume is within the range 0.0 to 1.0.
     * Adjusts the volume of currently playing music tracks to the new value.
     * @param volume New volume value, which will be clamped between 0.0 and 1.0.
     */
    public void setMusicVolume(float volume) {
        musicVolume = Math.max(0.0f, Math.min(volume, 1.0f)); // Clamp volume between 0 and 1
        for (Music music : musics.values()) {
            music.setVolume(musicVolume); // Apply clamped volume to all music tracks
        }
    }

    @Override
    public void dispose() {
        for (Sound sound : soundEffects.values()) {
            sound.dispose();
        }
        for (Music music : musics.values()) {
            music.dispose();
        }
    }
}
