package org.gitlab4j.api.utils;

/**
 * @deprecated use {@link org.gitlab4j.models.utils.JacksonJsonEnumHelper} instead
 */
@Deprecated
public class JacksonJsonEnumHelper<E extends Enum<E>> extends org.gitlab4j.models.utils.JacksonJsonEnumHelper<E> {
    public JacksonJsonEnumHelper(Class<E> enumType) {
        super(enumType, false);
    }

    public JacksonJsonEnumHelper(Class<E> enumType, boolean firstLetterCapitalized) {
        super(enumType, firstLetterCapitalized);
    }

    public JacksonJsonEnumHelper(Class<E> enumType, boolean firstLetterCapitalized, boolean camelCased) {
        super(enumType, firstLetterCapitalized, camelCased, false);
    }

    public JacksonJsonEnumHelper(
            Class<E> enumType, boolean firstLetterCapitalized, boolean camelCased, boolean preserveUnderscores) {
        super(enumType, firstLetterCapitalized, camelCased, preserveUnderscores);
    }
}
