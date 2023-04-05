package org.gitlab4j.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.gitlab4j.api.models.Markdown;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("integration")
@ExtendWith(SetupIntegrationTestExtension.class)
public class TestMarkdownApi extends AbstractIntegrationTest {

    private static final String EXPECTED_HTML_FOR_SPECIAL = "<p data-sourcepos=\"1:1-1:104\" dir=\"auto\">Hello world! <gl-emoji title=\"party popper\" data-name=\"tada\" data-unicode-version=\"6.0\">🎉</gl-emoji> <code>xml &lt;profiles&gt; &lt;version&gt;${maven-surefire-plugin.version}&lt;/version&gt; &lt;/profiles&gt;</code></p>";
    public static final String SPECIAL_CHAR_EXAMPLE = "Hello world! :tada: ```xml <profiles> <version>${maven-surefire-plugin.version}</version> </profiles>```";

    public static final String NORMAL_HTML_EXAMPLE = "<h1 data-sourcepos=\"1:1-1:4\" dir=\"auto\">\n" +
            "<a id=\"user-content-h1\" class=\"anchor\" href=\"#h1\" aria-hidden=\"true\"></a>H1</h1>\n" +
            "<h2 data-sourcepos=\"2:2-2:6\" dir=\"auto\">\n" +
            "<a id=\"user-content-h2\" class=\"anchor\" href=\"#h2\" aria-hidden=\"true\"></a>H2</h2>\n" +
            "<h3 data-sourcepos=\"3:2-3:7\" dir=\"auto\">\n" +
            "<a id=\"user-content-h3\" class=\"anchor\" href=\"#h3\" aria-hidden=\"true\"></a>H3</h3>\n" +
            "<h4 data-sourcepos=\"4:2-4:8\" dir=\"auto\">\n" +
            "<a id=\"user-content-h4\" class=\"anchor\" href=\"#h4\" aria-hidden=\"true\"></a>H4</h4>\n" +
            "<h5 data-sourcepos=\"5:2-5:9\" dir=\"auto\">\n" +
            "<a id=\"user-content-h5\" class=\"anchor\" href=\"#h5\" aria-hidden=\"true\"></a>H5</h5>\n" +
            "<h6 data-sourcepos=\"6:2-6:10\" dir=\"auto\">\n" +
            "<a id=\"user-content-h6\" class=\"anchor\" href=\"#h6\" aria-hidden=\"true\"></a>H6</h6>";

    private static GitLabApi gitLabApi;

    @BeforeAll
    public static void setUp() throws Exception {
        gitLabApi = baseTestSetup();
    }

    @BeforeEach
    public void beforeMethod() {
        assumeTrue(gitLabApi != null);
    }

    @Test
    public void testMarkdownWithSpecialCharacters() throws GitLabApiException {
        Markdown markdown = gitLabApi.getMarkdownApi().getMarkdown(SPECIAL_CHAR_EXAMPLE);

        assertEquals(EXPECTED_HTML_FOR_SPECIAL, markdown.getHtml());
    }

    @Test
    public void testMarkdownWithNormalText() throws GitLabApiException {
        Markdown markdown = gitLabApi.getMarkdownApi().getMarkdown("# H1 \n ## H2 \n ### H3 \n #### H4 \n ##### H5 \n ###### H6");

        assertEquals(NORMAL_HTML_EXAMPLE, markdown.getHtml());
    }
}