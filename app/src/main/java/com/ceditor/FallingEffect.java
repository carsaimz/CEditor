package com.ceditor;

import android.animation.ValueAnimator; import android.content.Context; import android.content.SharedPreferences; import android.graphics.*; import android.util.AttributeSet; import android.view.View;

import java.time.LocalDate; import java.time.temporal.ChronoUnit; import java.util.*;

public class FallingEffect extends View {

public enum EffectType {
    SNOW, RAIN, CONFETTI, BIRTHDAY
}

private EffectType effectType = EffectType.CONFETTI;
private List<Particle> particles;
private ValueAnimator animator;
private Bitmap bitmap;
private Canvas canvas;
private boolean isRunning = false;

private final Random random = new Random();
private List<Integer> themeColors = Arrays.asList(Color.YELLOW, Color.MAGENTA, Color.CYAN);
private String celebrationMessage = "";
private String birthdayMessage = "Happy Birthday!";
private final LocalDate launchDate = LocalDate.of(2021, 2, 2);

public FallingEffect(Context context) {
    super(context);
    calculateMessage();
}

public FallingEffect(Context context, AttributeSet attrs) {
    super(context, attrs);
    calculateMessage();
}

public FallingEffect(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    calculateMessage();
}

private void calculateMessage() {
    LocalDate today = LocalDate.now();
    long years = ChronoUnit.YEARS.between(launchDate, today);
    if (years <= 0) {
        celebrationMessage = "~";
    } else if (years == 1) {
        celebrationMessage = "1st Anniversary!";
    } else {
        celebrationMessage = years + getOrdinalSuffix((int) years) + " Anniversary!";
    }
}

private String getOrdinalSuffix(int number) {
    if (number >= 11 && number <= 13) return "th";
    switch (number % 10) {
        case 1: return "st";
        case 2: return "nd";
        case 3: return "rd";
        default: return "th";
    }
}

public void setEffectType(EffectType type) {
    this.effectType = type;
    if (isRunning) {
        stopEffect();
        startEffect();
    }
}

public void setThemeColors(List<Integer> colors) {
    this.themeColors = colors;
}

public void setBirthdayMessage(String message) {
    this.birthdayMessage = message;
}

public void startEffect() {
    if (getWidth() == 0 || getHeight() == 0) return;

    bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
    canvas = new Canvas(bitmap);
    particles = new ArrayList<>();

    int count = 60;
    for (int i = 0; i < count; i++) {
        float size = 4 + random.nextFloat() * 18;
        float speed = effectType == EffectType.RAIN ? 8 + random.nextFloat() * 10 :
                effectType == EffectType.SNOW ? 1 + random.nextFloat() * 3 :
                2 + random.nextFloat() * 6;
        int alpha = 150 + random.nextInt(106);
        int color = themeColors.get(random.nextInt(themeColors.size()));

        particles.add(new Particle(
                random.nextFloat() * getWidth(),
                random.nextFloat() * getHeight(),
                size,
                alpha,
                speed,
                color
        ));
    }

    if (effectType == EffectType.BIRTHDAY || effectType == EffectType.CONFETTI) {
        int repeatCount = 6;
        for (int i = 0; i < repeatCount; i++) {
            particles.add(new Particle(
                    random.nextFloat() * getWidth(),
                    random.nextFloat() * getHeight(),
                    36f,
                    255,
                    2 + random.nextFloat() * 3,
                    Color.WHITE,
                    effectType == EffectType.BIRTHDAY ? birthdayMessage : celebrationMessage
            ));
        }
    }

    animator = ValueAnimator.ofFloat(0, 1);
    animator.setDuration(3000);
    animator.setRepeatCount(ValueAnimator.INFINITE);

    animator.addUpdateListener(animation -> {
        bitmap.eraseColor(Color.TRANSPARENT);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        for (Particle p : particles) {
            p.y += p.speed;
            if (effectType == EffectType.SNOW) {
                p.x += random.nextFloat() * 2 - 1;
            }

            if (p.y > getHeight()) {
                p.y = 0;
                p.x = random.nextFloat() * getWidth();
            }

            paint.setAlpha(p.alpha);
            paint.setColor(p.color);

            if (p.isText()) {
                paint.setTextSize(p.size);
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTypeface(Typeface.DEFAULT_BOLD);
                canvas.drawText(p.text, p.x, p.y, paint);
            } else if (effectType == EffectType.RAIN) {
                canvas.drawLine(p.x, p.y, p.x, p.y + p.size * 3, paint);
            } else {
                canvas.drawCircle(p.x, p.y, p.size, paint);
            }
        }

        invalidate();
    });

    animator.start();
    isRunning = true;
}

public void stopEffect() {
    if (animator != null && animator.isRunning()) {
        animator.cancel();
    }
    if (bitmap != null) {
        bitmap.eraseColor(Color.TRANSPARENT);
    }
    invalidate();
    isRunning = false;
}

public void pauseEffect() {
    if (animator != null && animator.isRunning()) {
        animator.pause();
    }
}

public void resumeEffect() {
    if (animator != null && animator.isPaused()) {
        animator.resume();
    }
}

public boolean isRunning() {
    return isRunning;
}

public boolean isPaused() {
    return animator != null && animator.isPaused();
}

@Override
protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (bitmap != null) {
        canvas.drawBitmap(bitmap, 0, 0, null);
    }
}

@Override
protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    if (isRunning) {
        stopEffect();
        startEffect();
    }
}

private static class Particle {
    float x, y, size, speed;
    int alpha;
    int color;
    String text;

    Particle(float x, float y, float size, int alpha, float speed, int color) {
        this(x, y, size, alpha, speed, color, null);
    }

    Particle(float x, float y, float size, int alpha, float speed, int color, String text) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.alpha = alpha;
        this.speed = speed;
        this.color = color;
        this.text = text;
    }

    boolean isText() {
        return text != null;
    }
}

}