DBaaS vs DIY
============

|                                                                  | DBaaS | DIY |
|------------------------------------------------------------------|:-----:|:---:|
| Time to solution (initial design, setup, expertise)              |   ✔   |  ❌  |
| Dependence on specialists (retention, HR, availability)          |   ✔   |  ❌  |
| Costs to init (design, setup)                                    |   ✔   |  ❌  |
| Operational complexity (monitoring)                              |   ✔   |  ❌  |
| Operational costs (backup, upgrade)                              |   ✔   |  ❌  |
| Costs of performance (more CPU cycles for same budget)           |   ❌   |  ✔  |
| Costs of data volume (scaling with volume)                       |   ❌   |  ✔  |
| Control & Special features (DB or environment specific)          |   ❌   |  ✔  |
| Security in general (maintenance, patch level, monitoring)       |   ✔   |  ❌  |
| Specific security requirements (legal requirements, confidelity) |   ❌   |  ✔  |

Personal choice
===============
In my case (at this point in the course) using Cloud SQL would require
* additional effort of implementation
* additional costs of running the cluster (though still well within the initial credits)
* increase complexity of the CD pipeline
* sacrifice the strict isolation between namespaces created by CI
* or lose the flexibility of spinning them up
> "When you delete an instance, you can't reuse the name of the deleted 
> instance until one week from the deletion date."

So I've concluded that I stick with the current solution of StatefulSet.