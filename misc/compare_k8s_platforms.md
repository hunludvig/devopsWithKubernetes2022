Comparison of Kubernetes platforms
==================================

My original intention was to choose two Kubernetes platforms of two companies
with the most enterprise sounding background. You know, nothing fancy and cool
just the plain SLA and support contracts, the most streamlined way to keep
legacy applications on life support in this decade.
I've chosen Tanzu from VMware[^1] (acquired as PKS from Pivotal) and Ezmeral
from HPE[^2].

Tanzu offers great integration with existing VMware infrastructure, especially
the vSphere platform and storage solutions, some excellent tools to centralize
management and visualization of all your public cloud based and private clusters
under Tanzu Mission Control[^3]. Ezmeral's clear focus is on boosting
developers[^4] to migrate their applications, no matter from what era, to a new
shiny infrastructure squeezing the last drops of juice out of your bare metal
on-premise datacenters[^5].

They are both complex systems whose goal is "commoditizing the technology"[^6],
going after the same budget as platform teams and allow companies to concentrate
on their domains, focus on the "what" instead of the "how".

I've spent some length to research technical details of which platform to choose,
investigating in what exact tiny details they differ when I've stumbled upon the
article "Wrong Questions Only – What’s the Best Kubernetes Distro?"[^7] from
The CTO Adviser. This article (unlike the dozens of flyers and useless side by
side comparision sites I've visited[^8]) revealed that I'd need to spend way
more effort on actually evaluating these products to be able to make a use case
specific actual decision and the main aspects really won't be about Kubernetes
as such but all the integrations, convinience services offered for developers
and their pricing because "What’s the one thing that they are all trying to sell?
Hint, it isn’t container orchestration."[^7].

[^1]: https://tanzu.vmware.com/application-platform
[^2]: https://www.hpe.com/us/en/software/ezmeral-runtime.html
[^3]: https://tanzu.vmware.com/mission-control
[^4]: https://developer.hpe.com/blog/application-modernization-with-the-application-workbench/
[^5]: https://www.hpe.com/us/en/software/why-hpe.html
[^6]: https://thectoadvisor.com/blog/2020/09/24/the-demise-of-the-platform-team-lets-blame-openstack/
[^7]: https://thectoadvisor.com/blog/2020/10/12/wrong-questions-only-whats-the-best-kubernetes-distro/
[^8]: E.g.: https://sourceforge.net/software/compare/HPE-Ezmeral-vs-Rancher-vs-VMware-Tanzu/